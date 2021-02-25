package com.example.networkobserverdemo.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var hashCodeMainActivity: String = ""

    fun setLocalData(hashCode: Int) {
        hashCodeMainActivity = hashCode.toString()
    }
}