package com.mfomin.sunrise.data.repository

import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.data.model.CoordinatesEntity
import com.mfomin.sunrise.domain.exception.CachedSunriseNotAvailableException
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import sun.rmi.runtime.Log
import javax.inject.Inject

class SunriseInfoRepositoryImpl @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val remoteRepository: RemoteRepository,
    private val mapper: Mapper<CitySunriseEntity, CitySunrise>
) : SunriseInfoRepository {

    override fun clearCurrentLocationSunrise(): Completable {
        return cacheRepository.clearCurrentLocationSunrise()
    }

    override fun getCurrentLocationSunrise(lat: Double, lon: Double): Observable<CitySunrise> {
        val resultLocal =
            Single.zip(
                cacheRepository.isCurrentLocationSunriseCached(),
                cacheRepository.isCurrentLocationSunriseExpired(),
                BiFunction<Boolean, Boolean, Observable<CitySunriseEntity>> { isCached, isExpired ->
                    if (isCached && !isExpired)
                        cacheRepository.getCurrentLocationSunrise().toObservable()
                    else
                        Observable.error<CitySunriseEntity>(
                            Throwable(
                                if (isCached)
                                    "Cache is not available"
                                else
                                    "Cache is expired"
                            )
                        )
                })
                .toObservable()
                .switchMap { it }

        val citySunrise: Observable<CitySunriseEntity> =
            resultLocal.onErrorResumeNext { _: Throwable ->
                getRemoteSunriseInfo(lat, lon)
            }

        return citySunrise.map {
            mapper.from(it)
        }
    }

    override fun getCitySunrise(name: String, lat: Double, lon: Double): Observable<CitySunrise> {
        val resultLocal =
            Single.zip(
                cacheRepository.isCitySunriseCached(name),
                cacheRepository.isCitySunriseExpired(name),
                BiFunction<Boolean, Boolean, Observable<CitySunriseEntity>> { isCached, isExpired ->
                    if (isCached && !isExpired)
                        cacheRepository.getCityLocationSunrise(name).toObservable()
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
                .switchMap { it }

        val citySunrise: Observable<CitySunriseEntity> =
            resultLocal.onErrorResumeNext { _: Throwable ->
                getRemoteSunriseInfo(name, lat, lon)
            }

        return citySunrise.map {
            mapper.from(it)
        }
    }

    private fun getRemoteSunriseInfo(lat: Double, lon: Double): Observable<CitySunriseEntity> =
        getRemoteSunriseInfo(null, lat, lon)

    private fun getRemoteSunriseInfo(name: String?, lat: Double, lon: Double): Observable<CitySunriseEntity> =
        remoteRepository.getSunriseInfo(lat, lon).map {
            val result = CitySunriseEntity(0, System.currentTimeMillis(), name, CoordinatesEntity(lat, lon), it)
            saveCitySunrise(mapper.from(result))
                .subscribe(
                    { "qwe".toString() },
                    { it.printStackTrace() })
            result
        }.toObservable()

    override fun saveCitySunrise(citySunrise: CitySunrise): Completable {
        return cacheRepository.saveCitySunrise(mapper.to(citySunrise))
    }
}