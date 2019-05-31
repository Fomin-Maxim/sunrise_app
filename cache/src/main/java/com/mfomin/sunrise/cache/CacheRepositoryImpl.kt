package com.mfomin.sunrise.cache

import com.mfomin.sunrise.cache.model.CitySunriseCached
import com.mfomin.sunrise.cache.room.CitySunriseDao
import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.data.repository.CacheRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val citySunriseDao: CitySunriseDao,
    private val mapper: Mapper<CitySunriseCached, CitySunriseEntity>
) : CacheRepository {
    private val expirationTimeCurrentLocation = 3 * 60 * 60 * 1000
    private val expirationTimeCity = 6 * 60 * 60 * 1000

    override fun saveCitySunrise(citySunriseEntity: CitySunriseEntity): Completable {
        return citySunriseDao.insertCitySunrise(mapper.to(citySunriseEntity))
    }

    override fun isCurrentLocationSunriseCached(): Single<Boolean> =
        citySunriseDao.getCurrentLocationSunrise()
            .flatMap {
                Single.just(true)
            }
            .onErrorReturnItem(false)

    override fun isCitySunriseCached(name: String): Single<Boolean> =
        citySunriseDao.getCitySunrise(name)
            .flatMap {
                Single.just(true)
            }
            .onErrorReturnItem(false)

    override fun isCurrentLocationSunriseExpired(): Single<Boolean> =
        citySunriseDao.getCurrentLocationSunrise().map {
            System.currentTimeMillis() - it.date > expirationTimeCurrentLocation
        }

    override fun isCitySunriseExpired(name: String): Single<Boolean> =
        citySunriseDao.getCurrentLocationSunrise().map {
            System.currentTimeMillis() - it.date > expirationTimeCity
        }

    override fun clearCurrentLocationSunrise(): Completable =
        citySunriseDao.deleteCurrentLocationSunrise()

    override fun getCurrentLocationSunrise(): Single<CitySunriseEntity> =
        citySunriseDao.getCurrentLocationSunrise().map {
            mapper.from(it)
        }

    override fun getCityLocationSunrise(name: String): Single<CitySunriseEntity> =
        citySunriseDao.getCitySunrise(name).map {
            mapper.from(it)
        }
}