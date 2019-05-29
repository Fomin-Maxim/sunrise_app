package com.mfomin.sunrise.remote

import com.mfomin.sunrise.remote.model.RemoteSunriseInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SunriseApi {

    @GET("json")
    fun getSunriseByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<RemoteSunriseInfo>
}