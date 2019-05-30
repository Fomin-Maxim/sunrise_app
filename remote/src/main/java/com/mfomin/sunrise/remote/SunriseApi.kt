package com.mfomin.sunrise.remote

import com.mfomin.sunrise.remote.response.SunriseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SunriseApi {

    @GET("json")
    fun getSunriseByCoordinates(
        @Query("lat") lat: Double,
        @Query("lng") lon: Double
    ): Single<SunriseResponse>
}