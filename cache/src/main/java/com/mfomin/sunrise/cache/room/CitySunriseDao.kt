package com.mfomin.sunrise.cache.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mfomin.sunrise.cache.model.CitySunriseCached

@Dao
interface CitySunriseDao {

    @Query("DELETE FROM ${SunriseDatabase.TableCitySunrise.NAME} WHERE id = -1")
    fun deleteCurrentLocationSunrise(): Int

    @Query("SELECT * FROM ${SunriseDatabase.TableCitySunrise.NAME} WHERE id = -1 LIMIT 1")
    fun getCurrentLocationSunrise(): LiveData<CitySunriseCached>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCitySunrise(citySunriseCached: CitySunriseCached)
}