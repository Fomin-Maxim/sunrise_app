package com.mfomin.sunrise.app.presentation.sunrise

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.google.android.libraries.places.api.model.Place
import com.mfomin.sunrise.app.presentation.common.BaseViewModel
import com.mfomin.sunrise.app.util.livedata.Result
import com.mfomin.sunrise.common.scheduler.SchedulerProvider
import com.mfomin.sunrise.domain.location.LocationRepository
import com.mfomin.sunrise.domain.model.CitySunrise
import com.mfomin.sunrise.domain.networkconnection.NetworkConnection
import com.mfomin.sunrise.domain.networkconnection.NetworkStateListener
import com.mfomin.sunrise.domain.repository.SunriseInfoRepository
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class SunriseViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val sunriseInfoRepository: SunriseInfoRepository,
    private val schedulerProvider: SchedulerProvider,
    networkStateListener: NetworkStateListener,
    networkConnection: NetworkConnection
) : BaseViewModel() {

    val sunriseInfo = MutableLiveData<Result<CitySunrise>>()
    val networkConnected = MutableLiveData<Boolean>()

    val optionCurrentLocationChecked: ObservableBoolean = ObservableBoolean(true)
    val optionCityChecked: ObservableBoolean = ObservableBoolean(false)

    private val selectedPlace = BehaviorSubject.create<Place>()

    init {
        networkConnected.postValue(networkConnection.isConnected())

        networkStateListener.onNetConnected().subscribe {
            networkConnected.postValue(it)
        }.addTo(compositeDisposable)

        selectedPlace.subscribe { place ->
            getSunriseInfoForCity(place)
        }.addTo(compositeDisposable)
    }

    fun getLocationSunriseInfo() {
        locationRepository.getBestLastLocation()
            .switchMap {
                sunriseInfoRepository.getCurrentLocationSunrise(it.latitude, it.longitude)
            }
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                sunriseInfo.postValue(Result.inProgress())
            }
            .subscribe(
                {
                    sunriseInfo.postValue(Result.success(it))
                }, {
                    sunriseInfo.postValue(Result.failure(it.localizedMessage, it))
                    it.printStackTrace()
                })
            .addTo(compositeDisposable)
    }

    fun getSunriseInfoForCity(name: String, latitude: Double, longitude: Double) {
        sunriseInfoRepository.getCitySunrise(name, latitude, longitude)
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                sunriseInfo.postValue(Result.inProgress())
            }
            .subscribe(
                {
                    sunriseInfo.postValue(Result.success(it))
                },
                {
                    sunriseInfo.postValue(Result.failure(it.localizedMessage, it))
                    it.printStackTrace()
                })
            .addTo(compositeDisposable)
    }

    fun updateSunrise() {
        if (optionCurrentLocationChecked.get()) {
            getLocationSunriseInfo()
        } else {
            val place = selectedPlace.value
            if (place != null)
                getSunriseInfoForCity(place)
        }
    }

    private fun getSunriseInfoForCity(place: Place) {
        getSunriseInfoForCity(
            place.name ?: "",
            place.latLng!!.latitude,
            place.latLng!!.longitude
        )
    }

    fun setSelectedPlace(place: Place) {
        selectedPlace.onNext(place)
    }
}