package com.mfomin.sunrise.app

import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.mfomin.sunrise.app.di.DaggerAppComponent
import com.mfomin.sunrise.cache.di.DatabaseModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import com.google.android.libraries.places.internal.e
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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
