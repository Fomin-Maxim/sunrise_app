package com.mfomin.sunrise.cache.mapper

import com.mfomin.sunrise.cache.model.CoordinatesCached
import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.CoordinatesEntity
import javax.inject.Inject

open class CoordinatesCachedEntityMapper @Inject constructor() : Mapper<CoordinatesCached, CoordinatesEntity> {
    override fun from(type: CoordinatesCached) = CoordinatesEntity(
        type.lat,
        type.lon
    )

    override fun to(type: CoordinatesEntity) = CoordinatesCached(
        type.lat,
        type.lon
    )
}