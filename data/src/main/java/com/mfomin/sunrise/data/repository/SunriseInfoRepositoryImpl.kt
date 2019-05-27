package com.mfomin.sunrise.data.repository

import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import io.reactivex.Completable
import io.reactivex.Observable

class SunriseInfoRepositoryImpl : SunriseInfoRepository {
    override fun clearCurrentLocationSunrise(): Completable {
        TODO("not implemented")
    }

    override fun getCurrentLocationSurise(): Observable<CitySunrise> {
        TODO("not implemented")
    }

    override fun saveCitySunrise(citySunrise: CitySunrise): Completable {
        TODO("not implemented")
    }
}