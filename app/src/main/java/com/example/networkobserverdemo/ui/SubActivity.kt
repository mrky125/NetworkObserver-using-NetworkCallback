package com.example.networkobserverdemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.networkobserverdemo.R
import com.example.networkobserverdemo.databinding.ActivitySubBinding
import com.example.networkobserverdemo.viewmodel.SubViewModel

class SubActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Sub" + " mori"
    }

    private lateinit var viewModel: SubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate, $this")

        viewModel = ViewModelProvider.NewInstanceFactory().create(SubViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivitySubBinding>(this, R.layout.activity_sub)
        binding.viewModel = viewModel

        setupRestartMainActivity()
        Toast.makeText(this, R.string.view_created, Toast.LENGTH_LONG).show()
    }

    private fun setupRestartMainActivity() {
        viewModel.positiveButtonClick.observe(this) { clicked ->
            Log.d(TAG, "positiveButtonClick: $clicked")
            if (clicked) {
                startMainActivityWithClearingTask()
            }
        }
    }

    private fun startMainActivityWithClearingTask() {
        val intent = Intent(this, MainActivity::class.java).apply {
            // 既存のスタックを全て削除し、MainActivityを作り直す
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart, $this")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume, $this")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause, $this")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop, $this")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart, $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy, $this")
    }
}