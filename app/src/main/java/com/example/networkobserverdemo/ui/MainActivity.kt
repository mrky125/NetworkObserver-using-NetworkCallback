package com.example.networkobserverdemo.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.networkobserverdemo.R
import com.example.networkobserverdemo.databinding.ActivityMainBinding
import com.example.networkobserverdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Main" + " mori"
    }

    private lateinit var viewModel: MainViewModel

    private val receiveIntent = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
            Log.d(TAG, "onReceive, intent action: ${intent.action}")
                when (it.action) {
                    INTENT_ACTION_FINISH_APP -> {
                        Log.d(TAG, "finish app")
                        finish()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate, $this")
        setContentView(R.layout.activity_main)

        // set binding and viewModel
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel

        val filter = IntentFilter().apply {
            addAction(INTENT_ACTION_FINISH_APP)
        }
        registerReceiver(receiveIntent, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiveIntent) // Activityがバックグラウンド時もIntentを受け取りたいのでonDestroyで実行
    }
}

const val INTENT_ACTION_FINISH_APP = "com.example.networkobserverdemo_finish_app"