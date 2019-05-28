package com.mfomin.sunrise.remote

import com.mfomin.sunrise.remote.model.RemoteSunriseInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SunriseApi {

    @GET("json")
    fun getSunriseByCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Observable<RemoteSunriseInfo>
}