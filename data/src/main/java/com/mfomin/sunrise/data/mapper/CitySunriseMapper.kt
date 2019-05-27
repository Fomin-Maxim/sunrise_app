package com.mfomin.sunrise.data.mapper

import com.mfomin.sunrise.domain.db.mapper.SunriseInfoMapper
import javax.inject.Inject

open class CitySunriseMapper @Inject constructor(
    private val coordinatesMapper: CoordinatesMapper,
    private val sunriseInfoMapper: SunriseInfoMapper
)