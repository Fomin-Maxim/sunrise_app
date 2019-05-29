package com.mfomin.sunrise.data.repository

import com.mfomin.sunrise.data.model.SunriseInfoEntity
import io.reactivex.Single

interface RemoteRepository {
    fun getSunriseInfo(lat: Double, lon: Double): Single<SunriseInfoEntity>
}