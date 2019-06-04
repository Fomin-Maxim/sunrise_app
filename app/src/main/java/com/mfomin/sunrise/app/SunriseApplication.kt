package com.mfomin.sunrise.app

import com.google.android.libraries.places.api.Places
import com.mfomin.sunrise.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class SunriseApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()

        Places.initialize(this, getString(R.string.google_apikey))
        Places.createClient(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
    }
}
