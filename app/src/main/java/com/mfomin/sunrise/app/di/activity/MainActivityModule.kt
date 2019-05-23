package com.mfomin.sunrise.app.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.mfomin.sunrise.app.presentation.MainActivity
import com.mfomin.sunrise.app.presentation.sunrise.SunriseFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @Binds
    fun bindAppCompatActivity(activity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeSunriseFragment(): SunriseFragment
}