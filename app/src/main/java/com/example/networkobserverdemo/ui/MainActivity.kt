package com.example.networkobserverdemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set binding and viewModel
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            Log.d(TAG, "intent action: ${it.action}")
            when (it.action) {
                INTENT_ACTION_FINISH_APP -> finish()
            }
        }
    }
}

const val INTENT_ACTION_FINISH_APP = "com.example.networkobserverdemo_finish_app"