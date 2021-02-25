package com.example.networkobserverdemo.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.networkobserverdemo.R
import com.example.networkobserverdemo.viewmodel.SubViewModel

class SubActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Sub" + " mori"
    }

    private lateinit var viewModel: SubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate, $this")
        setContentView(R.layout.activity_sub)

        viewModel = ViewModelProvider.NewInstanceFactory().create(SubViewModel::class.java)
        setupRestartMainActivity()
        Toast.makeText(this, R.string.view_created, Toast.LENGTH_LONG).show()
    }

    private fun setupRestartMainActivity() {
        viewModel.positiveButtonClick.observe(this) {
            Log.d(TAG, "positiveButtonClick: $it")
            // TODO: Restart from MainActivity
        }
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