package com.mfomin.sunrise.cache.model

data class SunriseInfoCached(
    val sunrise: String,
    val sunset: String,
    val solarNoon: String,
    val dayLength: String,
    val civilTwilightBegin: String,
    val civilTwilightEnd: String,
    val nauticalTwilightBegin: String,
    val nauticalTwilightEnd: String,
    val astronomicalTwilightBegin: String,
    val astronomicalTwilightEnd: String
)