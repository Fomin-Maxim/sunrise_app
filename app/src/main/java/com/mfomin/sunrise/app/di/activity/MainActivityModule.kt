package com.mfomin.sunrise.app.di.activity

import com.mfomin.sunrise.app.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    fun contributeMainActivity(): MainActivity
}