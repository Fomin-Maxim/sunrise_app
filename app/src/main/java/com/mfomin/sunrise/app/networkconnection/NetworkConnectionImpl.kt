package com.mfomin.sunrise.app.networkconnection

import android.content.Context
import android.net.ConnectivityManager
import com.mfomin.sunrise.domain.networkconnection.NetworkConnection
import javax.inject.Inject

class NetworkConnectionImpl @Inject constructor(private val context: Context) : NetworkConnection {

    override fun isConnected(): Boolean {
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectionManager.activeNetworkInfo != null && connectionManager.activeNetworkInfo!!.isConnected
    }
}