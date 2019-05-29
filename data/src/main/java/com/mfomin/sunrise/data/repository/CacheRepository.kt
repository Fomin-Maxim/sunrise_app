package com.mfomin.sunrise.data.repository

import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.domain.model.CitySunrise
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CacheRepository {
    fun clearCurrentLocationSunrise(): Completable

    fun getCurrentLocationSunrise(): Single<CitySunriseEntity>

    fun saveCitySunrise(citySunriseEntity: CitySunriseEntity): Completable

    fun isCached(): Single<Boolean>

    fun isCurrentLocationExpired(): Single<Boolean>

    fun isCityLocationExpired(): Single<Boolean>

}