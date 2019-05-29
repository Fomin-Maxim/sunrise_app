package com.mfomin.sunrise.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfomin.sunrise.cache.model.CitySunriseCached
import com.mfomin.sunrise.cache.room.SunriseDatabase.Companion.VERSION

@Database(entities = [CitySunriseCached::class], version = VERSION)
abstract class SunriseDatabase : RoomDatabase() {

    abstract fun citySunriseDao(): CitySunriseDao

    object TableCitySunrise {
        const val NAME = "sunrise"
    }

    companion object {
        const val VERSION = 1
        const val NAME = "sunrise.db"
    }
}