package com.mfomin.sunrise.app.di

import androidx.lifecycle.ViewModelProvider
import com.mfomin.sunrise.app.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
