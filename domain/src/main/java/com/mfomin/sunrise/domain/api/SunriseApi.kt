package com.mfomin.sunrise.domain.api

import com.mfomin.sunrise.domain.model.Coordinates
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SunriseApi {

    @GET("json")
    fun getSunriseByCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Observable<Coordinates>
}