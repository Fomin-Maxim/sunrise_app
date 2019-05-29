package com.mfomin.sunrise.app

import com.mfomin.sunrise.app.di.DaggerAppComponent
import com.mfomin.sunrise.cache.di.DatabaseModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SunriseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
    }
}
