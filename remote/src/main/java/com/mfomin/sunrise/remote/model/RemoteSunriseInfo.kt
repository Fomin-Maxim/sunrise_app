package com.mfomin.sunrise.remote.model

import com.squareup.moshi.Json

data class RemoteSunriseInfo(
    @field:Json(name = "sunrise")
    val sunrise: String,

    @field:Json(name = "sunset")
    val sunset: String,

    @field:Json(name = "solar_noon")
    val solarNoon: String,

    @field:Json(name = "day_length")
    val dayLength: String,

    @field:Json(name = "civil_twilight_begin")
    val civilTwilightBegin: String,

    @field:Json(name = "civil_twilight_end")
    val civilTwilightEnd: String,

    @field:Json(name = "nautical_twilight_begin")
    val nauticalTwilightBegin: String,

    @field:Json(name = "nautical_twilight_end")
    val nauticalTwilightEnd: String,

    @field:Json(name = "astronomical_twilight_begin")
    val astronomicalTwilightBegin: String,

    @field:Json(name = "astronomical_twilight_end")
    val astronomicalTwilightEnd: String
)