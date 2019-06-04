package com.mfomin.sunrise.app.networkconnection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.mfomin.sunrise.domain.networkconnection.NetworkConnection
import com.mfomin.sunrise.domain.networkconnection.NetworkStateListener
import io.reactivex.Observable
import javax.inject.Inject


class NetworkStateListenerImpl @Inject constructor(
    private val context: Context,
    private val networkConnection: NetworkConnection
) : NetworkStateListener {

    override fun onNetConnected(): Observable<Boolean> {
        return Observable.create {
            val isConnectedToNetworkReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    it.onNext(networkConnection.isConnected())
                }
            }

            @Suppress("DEPRECATION")
            this.context.registerReceiver(
                isConnectedToNetworkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )

            it.setCancellable {
                this.context.unregisterReceiver(isConnectedToNetworkReceiver)
            }
        }
    }
}