package com.mfomin.sunrise.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mfomin.sunrise.cache.model.CitySunriseCached
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CitySunriseDao {

    @Query("DELETE FROM ${SunriseDatabase.TableCitySunrise.NAME} WHERE name is NULL")
    fun deleteCurrentLocationSunrise(): Completable

    @Query("SELECT * FROM ${SunriseDatabase.TableCitySunrise.NAME} WHERE name is NULL LIMIT 1")
    fun getCurrentLocationSunrise(): Maybe<CitySunriseCached>

    @Query("SELECT * FROM ${SunriseDatabase.TableCitySunrise.NAME} WHERE name = :cityName LIMIT 1")
    fun getCitySunrise(cityName: String): Maybe<CitySunriseCached>

    @Query("SELECT * FROM ${SunriseDatabase.TableCitySunrise.NAME}")
    fun getAllSunrises(): Observable<List<CitySunriseCached>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCitySunrise(citySunriseCached: CitySunriseCached): Completable
}