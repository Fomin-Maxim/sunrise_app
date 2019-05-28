package com.mfomin.sunrise.data.mapper

import javax.inject.Inject

open class CitySunriseMapper @Inject constructor(
    private val coordinatesMapper: CoordinatesMapper,
    private val sunriseInfoMapper: SunriseInfoMapper
)