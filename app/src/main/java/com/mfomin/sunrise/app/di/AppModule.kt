package com.mfomin.sunrise.app.di

import android.app.Application
import android.content.Context
import android.location.Location
import com.mfomin.sunrise.app.SunriseApplication
import com.mfomin.sunrise.app.location.LocationRepositoryImpl
import com.mfomin.sunrise.app.networkconnection.NetworkConnectionImpl
import com.mfomin.sunrise.app.util.AppSchedulerProvider
import com.mfomin.sunrise.cache.CacheRepositoryImpl
import com.mfomin.sunrise.cache.mapper.CitySunriseCachedEntityMapper
import com.mfomin.sunrise.cache.model.CitySunriseCached
import com.mfomin.sunrise.cache.room.CitySunriseDao
import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.common.scheduler.SchedulerProvider
import com.mfomin.sunrise.data.mapper.CitySunriseMapper
import com.mfomin.sunrise.data.model.CitySunriseEntity
import com.mfomin.sunrise.data.model.SunriseInfoEntity
import com.mfomin.sunrise.data.repository.CacheRepository
import com.mfomin.sunrise.data.repository.RemoteRepository
import com.mfomin.sunrise.data.repository.SunriseInfoRepositoryImpl
import com.mfomin.sunrise.domain.location.LocationRepository
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.networkconnection.NetworkConnection
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import com.mfomin.sunrise.remote.RemoteRepositoryImpl
import com.mfomin.sunrise.remote.SunriseApi
import com.mfomin.sunrise.remote.mapper.SunriseInfoEntityMapper
import com.mfomin.sunrise.remote.model.RemoteSunriseInfo
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
    fun provideLocationRepository(
        context: Context,
        uiScheduler: SchedulerProvider
    ): LocationRepository = LocationRepositoryImpl(context, uiScheduler)

    @Singleton
    @JvmStatic
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()


    @Singleton
    @JvmStatic
    @Provides
    fun provideSunriseInfoRepository(
         cacheRepository: CacheRepository,
         remoteRepository: RemoteRepository,
         networkConnection: NetworkConnection,
         mapper: CitySunriseMapper
     ): SunriseInfoRepository = SunriseInfoRepositoryImpl(
         cacheRepository,
         remoteRepository,
         networkConnection,
         mapper
     )

    @Singleton
    @JvmStatic
    @Provides
    fun provideCacheRepository(
         citySunriseDao: CitySunriseDao,
         mapper: CitySunriseCachedEntityMapper
     ): CacheRepository = CacheRepositoryImpl(citySunriseDao, mapper)

    @Singleton
    @JvmStatic
    @Provides
    fun provideRemoteRepository(
         sunriseApi: SunriseApi,
         mapper: SunriseInfoEntityMapper
     ): RemoteRepository = RemoteRepositoryImpl(sunriseApi, mapper)

    @Singleton
    @JvmStatic
    @Provides
    fun provideNetworkConnection(
         context: Context
     ): NetworkConnection = NetworkConnectionImpl(context)


}