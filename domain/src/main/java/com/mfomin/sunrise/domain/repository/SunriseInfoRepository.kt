package com.mfomin.sunrise.domain.repository

import com.mfomin.sunrise.domain.model.CitySunrise
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface SunriseInfoRepository {
    fun clearCurrentLocationSunrise(): Completable

    fun getCurrentLocationSunrise(lat: Double, lon: Double): Observable<CitySunrise>

    fun getCitySunrise(name: String, lat: Double, lon: Double): Observable<CitySunrise>

    fun saveCitySunrise(citySunrise: CitySunrise): Completable
}