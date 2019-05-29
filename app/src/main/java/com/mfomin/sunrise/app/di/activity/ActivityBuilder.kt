package com.mfomin.sunrise.app.di.activity

import com.mfomin.sunrise.app.di.ActivityScope
import com.mfomin.sunrise.app.di.PermissionModule
import com.mfomin.sunrise.app.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
}