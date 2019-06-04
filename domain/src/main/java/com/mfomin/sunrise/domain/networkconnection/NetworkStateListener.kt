package com.mfomin.sunrise.domain.networkconnection

import io.reactivex.Observable

interface NetworkStateListener {
    fun onNetConnected(): Observable<Boolean>
}