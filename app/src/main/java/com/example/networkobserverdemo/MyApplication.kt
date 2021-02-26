package com.example.networkobserverdemo

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.networkobserverdemo.data.source.remote.ApiClient
import com.example.networkobserverdemo.ui.SubActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyApplication : Application(), LifecycleObserver {
    
    companion object {
        private const val TAG = "MyApp" + " mori"
        private lateinit var networkCallback: NetworkCallback
    }

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val wifiCallback: (Boolean) -> Unit = { isWifi ->
        Log.d(TAG, "isWifi: $isWifi, request api")
        scope.launch {
            val result = ApiClient().getMockResponse()
            if (result) launchSubActivity()
        }
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        Log.d(TAG, "applicationContext: $applicationContext, baseContext: $baseContext")
        networkCallback = NetworkCallback(applicationContext, wifiCallback)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onApplicationCreate() {
        Log.d(TAG, "_onApplicationCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onApplicationStart() {
        Log.d(TAG, "_onApplicationStart")
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

    private fun launchSubActivity() {
        val intent = Intent(applicationContext, SubActivity::class.java).apply {
            // Activityの外（ActivityのContext以外）から起動する場合は NEW_TASK が必要
            // バックスタックを全て削除して起動したい（戻れないようにしたい）ので CLEAR_TASK
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        Log.d(TAG, "startActivity, context: $applicationContext")
        applicationContext.startActivity(intent)
    }
}