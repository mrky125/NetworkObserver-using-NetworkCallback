package com.example.networkobserverdemo.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ApiClient {

    suspend fun getMockResponse(): Boolean {
        return withContext(Dispatchers.IO) {
            delay(3000)
            true
        }
    }
}