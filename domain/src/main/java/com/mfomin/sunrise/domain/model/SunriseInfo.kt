package com.mfomin.sunrise.domain.model

import java.util.*

data class SunriseInfo(
    val sunrise: Date,
    val sunset: Date,
    val solarNoon: Date,
    val dayLength: Long,
    val civilTwilightBegin: Date,
    val civilTwilightEnd: Date,
    val nauticalTwilightBegin: Date,
    val nauticalTwilightEnd: Date,
    val astronomicalTwilightBegin: Date,
    val astronomicalTwilightEnd: Date
)