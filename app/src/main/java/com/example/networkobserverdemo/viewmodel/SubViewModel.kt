package com.example.networkobserverdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubViewModel : ViewModel() {

    private val _positiveButtonClick = MutableLiveData<Boolean>()
    val positiveButtonClick: LiveData<Boolean> = _positiveButtonClick

    fun onPositiveButtonClicked() {
        _positiveButtonClick.value = true
    }
}