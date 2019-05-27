package com.mfomin.sunrise.data.repository

import com.mfomin.sunrise.domain.model.CitySunrise
import io.reactivex.Completable
import io.reactivex.Observable

interface CacheRepository {
    fun clearCurrentLocationSunrise(): Completable

    fun getCurrentLocationSunrise(): Observable<CitySunrise>

    fun saveCitySunrise(citySunrise: CitySunrise): Completable
}