package com.example.networkobserverdemo

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log

class NetworkCallback(
    private val context: Context,
    private val wifiCallback: (Boolean) -> Unit
) : ConnectivityManager.NetworkCallback() {

    companion object {
        private const val TAG = "NetworkCallback" + " mori"
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Log.d(TAG, "-onAvailable")
        wifiCallback(isWifi(network))
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Log.d(TAG, "-onLost")
    }

    override fun onUnavailable() {
        super.onUnavailable()
        Log.d(TAG, "-onUnavailable")
    }

    override fun onCapabilitiesChanged(
        network: Network,
        networkCapabilities: NetworkCapabilities
    ) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        Log.d(TAG, "-onCapabilitiesChanged")
    }

    private fun isWifi(network: Network): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(network)
        val isWifi = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
        Log.d(TAG, "isWifi: $isWifi, $capabilities: capabilities")
        return isWifi
    }
}