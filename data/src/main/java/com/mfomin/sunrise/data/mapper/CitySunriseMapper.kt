package com.mfomin.sunrise.data.mapper

import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.domain.model.CitySunrise
import javax.inject.Inject

open class CitySunriseMapper @Inject constructor(
    private val coordinatesMapper: CoordinatesMapper,
    private val sunriseInfoMapper: SunriseInfoMapper
) : Mapper<CitySunriseEntity, CitySunrise> {
    override fun from(type: CitySunriseEntity) = CitySunrise(
        type.id,
        type.date,
        type.name,
        coordinatesMapper.from(type.coordinates),
        sunriseInfoMapper.from(type.sunriseInfo)
    )

    override fun to(type: CitySunrise) = CitySunriseEntity(
        type.id,
        type.date,
        type.name,
        coordinatesMapper.to(type.coordinates),
        sunriseInfoMapper.to(type.sunriseInfo)
    )

}