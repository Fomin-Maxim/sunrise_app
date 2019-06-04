package com.mfomin.sunrise.app.presentation.sunrise

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
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
    val selectedPlace = MutableLiveData<SelectedPlace>()

    val optionCurrentLocationChecked: ObservableBoolean = ObservableBoolean(true)
    val optionCityChecked: ObservableBoolean = ObservableBoolean(false)

    private val selectedPlaceSubject = BehaviorSubject.create<SelectedPlace>()

    init {
        networkConnected.postValue(networkConnection.isConnected())

        networkStateListener.onNetConnected().subscribe {
            networkConnected.postValue(it)
            updateSunrise()
        }.addTo(compositeDisposable)

        selectedPlaceSubject.subscribe { place ->
            getSunriseInfoForCity(place)
            selectedPlace.postValue(place)
        }.addTo(compositeDisposable)

        optionCityChecked.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                sunriseInfo.postValue(Result.created())
                updateSunrise()
            }
        })
    }

    private fun getLocationSunriseInfo() {
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

    private fun getSunriseInfoForCity(name: String, latitude: Double, longitude: Double) {
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
        if (optionCurrentLocationChecked.get() && !optionCityChecked.get()) {
            getLocationSunriseInfo()
        } else {
            val place = selectedPlaceSubject.value
            if (place != null)
                getSunriseInfoForCity(place)
        }
    }

    private fun getSunriseInfoForCity(selectedPlace: SelectedPlace) {
        selectedPlace.place?.let {
            getSunriseInfoForCity(
                it.name ?: "",
                it.latLng!!.latitude,
                it.latLng!!.longitude
            )
        }
    }

    fun setSelectedPlace(selectedPlace: SelectedPlace) {
        selectedPlaceSubject.onNext(selectedPlace)
    }

    fun clearSelectedPlace() {
        selectedPlaceSubject.onNext(SelectedPlace(null))
        sunriseInfo.postValue(Result.created())
    }
}