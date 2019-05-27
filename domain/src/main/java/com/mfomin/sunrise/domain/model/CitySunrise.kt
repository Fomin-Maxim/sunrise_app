package com.mfomin.sunrise.domain.model

data class CitySunrise(
    var id: Int,
    var date: Long,
    var name: String,
    val coordinates: Coordinates,
    val sunriseInfo: SunriseInfo
)