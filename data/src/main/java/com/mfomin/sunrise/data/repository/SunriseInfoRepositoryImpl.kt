package com.mfomin.sunrise.data.repository

import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.data.model.CoordinatesEntity
import com.mfomin.sunrise.domain.exception.CachedSunriseNotAvailableException
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.networkconnection.NetworkConnection
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class SunriseInfoRepositoryImpl @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val remoteRepository: RemoteRepository,
    private val networkConnection: NetworkConnection,
    private val mapper: Mapper<CitySunriseEntity, CitySunrise>
) : SunriseInfoRepository {
    override fun clearCurrentLocationSunrise(): Completable {
        return cacheRepository.clearCurrentLocationSunrise()
    }

    override fun getCurrentLocationSunrise(lat: Double, lon: Double): Observable<CitySunrise> {
        val citySunrise: Observable<CitySunriseEntity> =
            if (networkConnection.isConnected()) {
                getRemoteSunriseInfo(lat, lon)
            } else {
                Observable.concat(
                    Single.zip(
                        cacheRepository.isCached(),
                        cacheRepository.isCurrentLocationExpired(),
                        BiFunction<Boolean, Boolean, Observable<CitySunriseEntity>> { isCached, isExpired ->
                            if (isCached && !isExpired)
                                cacheRepository.getCurrentLocationSunrise().toObservable()
                            else
                                Observable.error(
                                    CachedSunriseNotAvailableException(
                                        if (isCached)
                                            "Cache is not available"
                                        else
                                            "Cache is expired"
                                    )
                                )
                        })
                        .toObservable()
                        .switchMap { it },
                    getRemoteSunriseInfo(lat, lon)
                )
            }

        return citySunrise.map {
            mapper.from(it)
        }
    }

    private fun getRemoteSunriseInfo(lat: Double, lon: Double): Observable<CitySunriseEntity> {
        //TODO save to cache with city name
        return remoteRepository.getSunriseInfo(lat, lon).map {
            CitySunriseEntity(1, System.currentTimeMillis(), "", CoordinatesEntity(lat, lon), it)
        }.toObservable()
    }

    override fun saveCitySunrise(citySunrise: CitySunrise): Completable {
        return cacheRepository.saveCitySunrise(mapper.to(citySunrise))
    }
}