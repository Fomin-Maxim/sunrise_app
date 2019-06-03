package com.mfomin.sunrise.app.presentation.sunrise

import androidx.lifecycle.MutableLiveData
import com.google.android.libraries.places.internal.it
import com.mfomin.sunrise.app.presentation.common.BaseViewModel
import com.mfomin.sunrise.app.util.livedata.Result
import com.mfomin.sunrise.common.scheduler.SchedulerProvider
import com.mfomin.sunrise.domain.location.LocationRepository
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.networkconnection.NetworkStateListener
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import java.util.*
import javax.inject.Inject


class SunriseViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val sunriseInfoRepository: SunriseInfoRepository,
    private val schedulerProvider: SchedulerProvider,
    private val networkStateListener: NetworkStateListener
) : BaseViewModel() {

    val sunriseInfo = MutableLiveData<Result<CitySunrise>>()

    val networkConnected = MutableLiveData<Boolean>()

    init {
        networkStateListener.onNetConnected().subscribe {
            networkConnected.postValue(it)
        }.addTo(compositeDisposable)
    }

    fun getSunriseInfo() {
        locationRepository.getBestLastLocation()
            .switchMap {
                sunriseInfoRepository.getCurrentLocationSunrise(it.latitude, it.longitude)
            }.subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                sunriseInfo.postValue(Result.inProgress())
            }
            .doOnNext {
                sunriseInfo.postValue(Result.success(it))
            }
            .doOnError {
                sunriseInfo.postValue(Result.failure(it.localizedMessage, it))
            }
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun getSunriseInfoForCity(name: String, latitude: Double, longitude: Double) {
        sunriseInfoRepository.getCitySunrise(name, latitude, longitude)
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                sunriseInfo.postValue(Result.inProgress())
            }
            .doOnNext {
                sunriseInfo.postValue(Result.success(it))
            }
            .doOnError {
                sunriseInfo.postValue(Result.failure(it.localizedMessage, it))
            }
            .subscribe()
            .addTo(compositeDisposable)
    }
}