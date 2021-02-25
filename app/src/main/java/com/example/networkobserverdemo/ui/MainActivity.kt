package com.example.networkobserverdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.networkobserverdemo.R
import com.example.networkobserverdemo.databinding.ActivityMainBinding
import com.example.networkobserverdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set binding and viewModel
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.setLocalData(this.hashCode())
    }
}