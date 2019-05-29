package com.mfomin.sunrise.app.di

import android.app.Application
import android.content.Context
import com.mfomin.sunrise.app.location.LocationRepositoryImpl
import com.mfomin.sunrise.app.permission.PermissionManager
import com.mfomin.sunrise.app.permission.PermissionManagerImpl
import com.mfomin.sunrise.app.presentation.MainActivity
import com.mfomin.sunrise.common.scheduler.SchedulerProvider
import com.mfomin.sunrise.domain.location.LocationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    @JvmStatic
    fun provideLocationProvider(
        context: Context,
        uiScheduler: SchedulerProvider
    ): LocationRepository = LocationRepositoryImpl(context, uiScheduler)

}