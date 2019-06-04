package com.mfomin.sunrise.remote.model

import com.squareup.moshi.Json
import java.util.*

data class RemoteSunriseInfo(
    @field:Json(name = "sunrise")
    val sunrise: Date,

    @field:Json(name = "sunset")
    val sunset: Date,

    @field:Json(name = "solar_noon")
    val solarNoon: Date,

    @field:Json(name = "day_length")
    val dayLength: Long,

    @field:Json(name = "civil_twilight_begin")
    val civilTwilightBegin: Date,

    @field:Json(name = "civil_twilight_end")
    val civilTwilightEnd: Date,

    @field:Json(name = "nautical_twilight_begin")
    val nauticalTwilightBegin: Date,

    @field:Json(name = "nautical_twilight_end")
    val nauticalTwilightEnd: Date,

    @field:Json(name = "astronomical_twilight_begin")
    val astronomicalTwilightBegin: Date,

    @field:Json(name = "astronomical_twilight_end")
    val astronomicalTwilightEnd: Date
)