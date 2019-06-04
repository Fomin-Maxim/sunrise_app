package com.mfomin.sunrise.remote.di

import com.mfomin.sunrise.remote.SunriseApi
import com.mfomin.sunrise.remote.retrofit.MainRxErrorHandlingCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
open class NetworkModule {

    companion object {
        val instance = NetworkModule()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            loggingInterceptors.forEach {
                addNetworkInterceptor(it)
            }
        }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    @Named("sunrise_sunset_api")
    fun provideRetrofitMain(okHttpClient: OkHttpClient, converterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.sunrise-sunset.org")
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(MainRxErrorHandlingCallAdapterFactory.createAsync())
            .build()
    }

    @Singleton
    @Provides
    fun createMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @Provides
    open fun provideSunriseApi(
        @Named("sunrise_sunset_api")
        retrofit: Retrofit
    ): SunriseApi {
        return retrofit.create(SunriseApi::class.java)
    }

    @Singleton
    @Provides
    @IntoSet
    fun provideNetworkLogger(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
}


