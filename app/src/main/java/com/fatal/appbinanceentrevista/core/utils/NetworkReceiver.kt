package com.fatal.appbinanceentrevista.core.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.fatal.appbinanceentrevista.data.dataSource.remote.websocket.BinanceWebSocketService


class NetworkReceiver(private val context: Context) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            startWebSocketService()
        }

        override fun onLost(network: Network) {
            stopWebSocketService()
        }
    }

    fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun startWebSocketService() {
        val intent = Intent(context, BinanceWebSocketService::class.java)
        context.startService(intent)
    }

    private fun stopWebSocketService() {
        val intent = Intent(context, BinanceWebSocketService::class.java)
        context.stopService(intent)
    }
}