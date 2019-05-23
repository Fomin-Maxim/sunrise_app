package com.mfomin.sunrise.app.di

import com.mfomin.sunrise.app.SunriseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = []
)
interface AppComponent : AndroidInjector<SunriseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(app: SunriseApplication): Builder

        fun build() : AppComponent
    }

    override fun inject(instance: SunriseApplication)
}