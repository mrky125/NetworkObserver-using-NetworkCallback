package com.example.networkobserverdemo.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    interface MainViewNavigator {
        fun onStartSubActivity()
    }

    private var navigator: MainViewNavigator? = null

    fun setNavigator(navigator: MainViewNavigator) {
        this.navigator = navigator
    }

    fun unsetNavigator() {
        navigator = null
    }

    fun startSubActivity() {
        navigator?.onStartSubActivity()
    }
}