package com.mfomin.sunrise.domain.repository

import com.mfomin.sunrise.domain.model.CitySunrise
import io.reactivex.Completable
import io.reactivex.Observable

interface SunriseInfoRepository {
    fun clearCurrentLocationSunrise(): Completable

    fun getCurrentLocationSunrise(lat: Double, lon: Double): Observable<CitySunrise>

    fun saveCitySunrise(citySunrise: CitySunrise): Completable
}