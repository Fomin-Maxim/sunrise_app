package com.mfomin.sunrise.remote

import com.mfomin.sunrise.common.mapper.MapperFrom
import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.data.model.SunriseInfoEntity
import com.mfomin.sunrise.data.repository.RemoteRepository
import com.mfomin.sunrise.remote.model.RemoteSunriseInfo
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val sunriseApi: SunriseApi,
    private val mapper: MapperFrom<RemoteSunriseInfo, SunriseInfoEntity>
) : RemoteRepository {
    override fun getSunriseInfo(lat: Double, lon: Double): Single<SunriseInfoEntity> =
        sunriseApi.getSunriseByCoordinates(lat, lon)
            .map {
                mapper.from(it)
            }
}