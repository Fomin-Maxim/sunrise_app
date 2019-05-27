package com.mfomin.sunrise.cache.mapper

import javax.inject.Inject

open class CitySunriseCachedEntityMapper @Inject constructor(
    private val coordinatesCachedEntityMapper: CoordinatesCachedEntityMapper,
    private val sunriseInfoCachedEntityMapper: SunriseInfoCachedEntityMapper
)