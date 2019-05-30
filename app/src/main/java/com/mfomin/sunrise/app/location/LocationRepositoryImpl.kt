package com.mfomin.sunrise.app.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import com.mfomin.sunrise.app.R
import com.mfomin.sunrise.app.permission.PermissionRequiredException
import com.mfomin.sunrise.common.scheduler.SchedulerProvider
import com.mfomin.sunrise.domain.location.SimpleLocation
import com.mfomin.sunrise.domain.location.LocationRepository
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject

@Suppress("RemoveRedundantQualifierName")
class LocationRepositoryImpl @Inject constructor(
    private val context: Context,
    private val schedulers: SchedulerProvider
) : LocationRepository {

    private val manager by lazy {
        this.context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val providers by lazy {
        arrayOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)
    }

    private val enabledProviders
        get() = this.providers.filter { this.manager.isProviderEnabled(it) }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION])
    override fun getBestLastLocation(): Observable<SimpleLocation> = Observable.create<SimpleLocation> {
        val location = this.internalGetBestLastLocation()
        if (location != null) {
            it.onNext(this.createLocation(location))
        }

        it.onComplete()
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION])
    override fun getLocation(): Observable<SimpleLocation> = Observable
        .merge(this.getBestLastLocation(), this.createGetUpdatedLocation())
        .observeOn(this.schedulers.ui())

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION])
    override fun getUpdatedLocation(): Observable<SimpleLocation> =
        this.createGetUpdatedLocation().observeOn(this.schedulers.ui())

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this.context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            throw PermissionRequiredException(
                this.context.getString(R.string.error_permission_required_location)
            )
        }
    }

    private fun createGetUpdatedLocation() = Observable.create<SimpleLocation> {
        this.checkLocationPermission()

        val listener = LocationListenerImpl(it)

        for (provider in this.enabledProviders) {
            this.manager.requestLocationUpdates(
                provider,
                0L,
                0f,
                listener,
                Looper.getMainLooper()
            )
        }

        it.setCancellable {
            this.manager.removeUpdates(listener)
        }
    }

    private fun createLocation(location: Location) = object : SimpleLocation {
        override var latitude = location.latitude
        override var longitude = location.longitude
    }

    private fun internalGetBestLastLocation(): Location? {
        this.checkLocationPermission()

        var bestLocation: Location? = null

        for (provider in this.enabledProviders) {
            this.manager.getLastKnownLocation(provider)?.let {
                if (it.isBetterThan(bestLocation)) {
                    bestLocation = it
                }
            }
        }

        return bestLocation
    }

    private fun Location.isBetterThan(previousLocation: Location?) =
        previousLocation == null || this.accuracy < previousLocation.accuracy

    inner class LocationListenerImpl(private val emitter: ObservableEmitter<SimpleLocation>) : LocationListener {

        private var bestLocation: Location? = null

        override fun onLocationChanged(location: Location) {
            if (location.isBetterThan(this.bestLocation)) {
                this.emitter.onNext(createLocation(location))

                this.bestLocation = location
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String?) {}
    }
}