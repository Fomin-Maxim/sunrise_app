package com.mfomin.sunrise.cache.di

import android.app.Application
import androidx.room.Room
import com.mfomin.sunrise.cache.room.CitySunriseDao
import com.mfomin.sunrise.cache.room.SunriseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        val instance = DatabaseModule()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): SunriseDatabase =
        Room.databaseBuilder(
            app.applicationContext, SunriseDatabase::class.java, SunriseDatabase.NAME
        ).build()

    @Singleton
    @Provides
    fun provideCitySunriseDao(db: SunriseDatabase): CitySunriseDao = db.citySunriseDao()
}