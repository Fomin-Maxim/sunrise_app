package com.mfomin.sunrise.app.di

import android.app.Application
import android.content.Context
import com.mfomin.sunrise.app.SunriseApplication
import com.mfomin.sunrise.app.di.activity.ActivityBuilder
import com.mfomin.sunrise.cache.di.DatabaseModule
import com.mfomin.sunrise.remote.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<SunriseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(app: Application): Builder
//        @BindsInstance
//        fun bindDatabase(databaseModule: DatabaseModule): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: SunriseApplication)
}