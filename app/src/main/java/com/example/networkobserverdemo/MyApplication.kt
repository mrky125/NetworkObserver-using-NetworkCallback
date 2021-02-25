package com.example.networkobserverdemo

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

class MyApplication : Application(), LifecycleObserver {
    
    companion object {
        private const val TAG = "MyApp" + " mori"
        private lateinit var networkCallback: NetworkCallback
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        Log.d(TAG, "applicationContext: $applicationContext, baseContext: $baseContext")
        networkCallback = NetworkCallback(applicationContext)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onApplicationCreate() {
        Log.d(TAG, "_onApplicationCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onApplicationStart() {
        Log.d(TAG, "_onApplicationStart")
        Log.d(TAG, "requestNetwork")
        requestNetwork()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onApplicationResume() {
//        Log.d(TAG, "_onApplicationResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onApplicationPause() {
//        Log.d(TAG, "_onApplicationPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onApplicationStop() {
        Log.d(TAG, "_onApplicationStop")
        Log.d(TAG, "unregisterNetwork")
        unregisterNetwork()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onApplicationDestroy() {
        // Never dispatched
        Log.d(TAG, "_onApplicationDestroy")
    }

    private fun requestNetwork() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        Log.d(TAG, "cm:$cm")
        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        cm.requestNetwork(request, networkCallback)
        Log.d(TAG, "requested network")
    }

    private fun unregisterNetwork() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        Log.d(TAG, "cm:$cm")
        cm.unregisterNetworkCallback(networkCallback)
        Log.d(TAG, "released network request")
    }
}