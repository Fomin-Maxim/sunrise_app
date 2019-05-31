package com.mfomin.sunrise.data.repository

import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.domain.model.CitySunrise
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CacheRepository {
    fun clearCurrentLocationSunrise(): Completable

    fun getCurrentLocationSunrise(): Single<CitySunriseEntity>

    fun getCityLocationSunrise(name: String): Single<CitySunriseEntity>

    fun saveCitySunrise(citySunriseEntity: CitySunriseEntity): Completable

    fun isCurrentLocationSunriseExpired(): Single<Boolean>

    fun isCitySunriseExpired(name: String): Single<Boolean>

    fun isCitySunriseCached(name: String): Single<Boolean>

    fun isCurrentLocationSunriseCached(): Single<Boolean>
}