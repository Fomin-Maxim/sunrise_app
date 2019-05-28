package com.mfomin.sunrise.remote.mapper

import com.mfomin.sunrise.common.mapper.MapperFrom
import com.mfomin.sunrise.data.model.CoordinatesEntity
import com.mfomin.sunrise.remote.model.RemoteCoordinates
import javax.inject.Inject

open class CoordinatesEntityMapper @Inject constructor() : MapperFrom<RemoteCoordinates, CoordinatesEntity> {
    override fun from(type: RemoteCoordinates) = CoordinatesEntity(type.lat, type.lon)
}