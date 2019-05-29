package com.mfomin.sunrise.app.presentation.sunrise

import androidx.lifecycle.MutableLiveData
import com.mfomin.sunrise.app.presentation.common.BaseViewModel
import com.mfomin.sunrise.common.scheduler.SchedulerProvider
import com.mfomin.sunrise.domain.location.LocationRepository
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import javax.inject.Inject
import com.mfomin.sunrise.app.util.livedata.Result
import io.reactivex.rxkotlin.addTo

class SunriseViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val sunriseInfoRepository: SunriseInfoRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val sunriseInfo = MutableLiveData<Result<CitySunrise>>()

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
}