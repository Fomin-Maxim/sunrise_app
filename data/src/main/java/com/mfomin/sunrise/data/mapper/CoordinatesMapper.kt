package com.mfomin.sunrise.data.mapper

import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.CoordinatesEntity
import com.mfomin.sunrise.domain.model.Coordinates
import javax.inject.Inject

open class CoordinatesMapper @Inject constructor() : Mapper<CoordinatesEntity, Coordinates> {
    override fun from(type: CoordinatesEntity) = Coordinates(
        type.lat,
        type.lon
    )

    override fun to(type: Coordinates) = CoordinatesEntity(
        type.lat,
        type.lon
    )
}