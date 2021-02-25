package com.example.networkobserverdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.networkobserverdemo.R
import com.example.networkobserverdemo.databinding.ActivityMainBinding
import com.example.networkobserverdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainViewModel.MainViewNavigator {

    private val viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set binding and viewModel
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }

    override fun onStartSubActivity() {
        val intent = Intent(this, SubActivity::class.java)
        startActivity(intent)
    }
}