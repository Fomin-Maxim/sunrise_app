package com.mfomin.sunrise.app.di.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.mfomin.sunrise.app.di.FragmentScope
import com.mfomin.sunrise.app.di.PermissionModule
import com.mfomin.sunrise.app.di.ViewModelKey
import com.mfomin.sunrise.app.permission.PermissionManager
import com.mfomin.sunrise.app.permission.PermissionManagerImpl
import com.mfomin.sunrise.app.presentation.MainActivity
import com.mfomin.sunrise.app.presentation.sunrise.SunriseFragment
import com.mfomin.sunrise.app.presentation.sunrise.SunriseViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ActivityModule {

    @Binds
    abstract fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [PermissionModule::class])
    abstract fun contributeSunriseFragment(): SunriseFragment

    @Binds
    @IntoMap
    @ViewModelKey(SunriseViewModel::class)
    abstract fun bindSunriseViewModel(
        sunriseViewModel: SunriseViewModel
    ): ViewModel


}