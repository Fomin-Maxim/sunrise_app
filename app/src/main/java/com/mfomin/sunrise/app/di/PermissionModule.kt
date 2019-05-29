package com.mfomin.sunrise.app.di

import com.mfomin.sunrise.app.permission.PermissionManager
import com.mfomin.sunrise.app.permission.PermissionManagerImpl
import com.mfomin.sunrise.app.presentation.sunrise.SunriseFragment
import dagger.Module
import dagger.Provides


@Module
object PermissionModule {

    @Provides
    @FragmentScope
    @JvmStatic
    fun providePermissionClient(fragment: SunriseFragment): PermissionManager =
        PermissionManagerImpl(fragment)

}