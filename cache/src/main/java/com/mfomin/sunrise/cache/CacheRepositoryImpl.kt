package com.mfomin.sunrise.cache

import com.mfomin.sunrise.cache.mapper.CitySunriseCachedEntityMapper
import com.mfomin.sunrise.cache.room.CitySunriseDao
import com.mfomin.sunrise.data.repository.CacheRepository
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val citySunriseDao: CitySunriseDao,
    private val mapper: CitySunriseCachedEntityMapper
) : CacheRepository {
    override fun clearCurrentLocationSunrise(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentLocationSunrise(): Observable<CitySunrise> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveCitySunrise(citySunrise: CitySunrise): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}