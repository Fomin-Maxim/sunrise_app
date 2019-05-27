package com.mfomin.sunrise.app.di

import com.mfomin.sunrise.app.SunriseApplication
import com.mfomin.sunrise.app.di.activity.ActivityModule
import com.mfomin.sunrise.app.di.activity.MainActivityModule
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
        MainActivityModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<SunriseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(app: SunriseApplication): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: SunriseApplication)
}