package com.mfomin.sunrise.app.presentation.sunrise

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mfomin.sunrise.app.presentation.common.BaseViewModel
import com.mfomin.sunrise.common.scheduler.SchedulerProvider
import com.mfomin.sunrise.domain.location.LocationRepository
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import javax.inject.Inject
import com.mfomin.sunrise.app.util.livedata.Result
import io.reactivex.rxkotlin.addTo
import com.google.android.libraries.places.internal.e
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.places.internal.i
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.AutocompleteSessionToken


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