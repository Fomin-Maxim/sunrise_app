package com.mfomin.sunrise.data.model

data class CitySunriseEntity(
    var id: Int,
    var date: Long,
    var name: String,
    val coordinates: CoordinatesEntity,
    val sunriseInfo: SunriseInfoEntity
)