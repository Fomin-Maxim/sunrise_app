package com.mfomin.sunrise.app.di.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.mfomin.sunrise.app.di.ViewModelKey
import com.mfomin.sunrise.app.presentation.MainActivity
import com.mfomin.sunrise.app.presentation.sunrise.SunriseFragment
import com.mfomin.sunrise.app.presentation.sunrise.SunriseViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ActivityModule {

    @Binds
    fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeSunriseFragment(): SunriseFragment

    @Binds
    @IntoMap
    @ViewModelKey(SunriseViewModel::class)
    fun bindSunriseViewModel(
        sunriseViewModel: SunriseViewModel
    ): ViewModel
}