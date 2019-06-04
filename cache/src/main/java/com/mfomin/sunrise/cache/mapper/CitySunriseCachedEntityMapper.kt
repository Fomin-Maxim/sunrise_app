package com.mfomin.sunrise.cache.mapper

import com.mfomin.sunrise.cache.model.CitySunriseCached
import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.CitySunriseEntity
import javax.inject.Inject

open class CitySunriseCachedEntityMapper @Inject constructor(
    private val coordinatesCachedEntityMapper: CoordinatesCachedEntityMapper,
    private val sunriseInfoCachedEntityMapper: SunriseInfoCachedEntityMapper
) : Mapper<CitySunriseCached, CitySunriseEntity> {
    override fun from(type: CitySunriseCached) = CitySunriseEntity(
        type.id,
        type.date,
        type.name,
        coordinatesCachedEntityMapper.from(type.coordinatesCached),
        sunriseInfoCachedEntityMapper.from(type.sunriseInfoCached)
    )

    override fun to(type: CitySunriseEntity) = CitySunriseCached(
        0,
        type.name,
        type.date,
        coordinatesCachedEntityMapper.to(type.coordinates),
        sunriseInfoCachedEntityMapper.to(type.sunriseInfo)
    )
}