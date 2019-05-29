package com.mfomin.sunrise.domain.location

import io.reactivex.Observable

interface LocationRepository {
    fun getBestLastLocation(): Observable<SimpleLocation>

    fun getLocation(): Observable<SimpleLocation>

    fun getUpdatedLocation(): Observable<SimpleLocation>
}