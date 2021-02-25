package com.example.networkobserverdemo

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.example.networkobserverdemo.ui.SubActivity

class NetworkCallback(private val context: Context) : ConnectivityManager.NetworkCallback() {

    companion object {
        private const val TAG = "NetworkCallback" + " mori"
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Log.d(TAG, "-onAvailable")
        if (isWifi(network)) {
            val intent = Intent(context, SubActivity::class.java).apply {
                // Activityの外（ActivityのContext以外）から起動する場合は NEW_TASK が必要
                // バックスタックを全て削除して起動したい（戻れないようにしたい）ので CLEAR_TASK
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            Log.d(TAG, "startActivity, context: $context")
            context.startActivity(intent)
        }
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