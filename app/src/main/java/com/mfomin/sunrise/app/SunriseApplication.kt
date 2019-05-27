package com.mfomin.sunrise.app

import com.mfomin.sunrise.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SunriseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
    }
}
