package com.mfomin.sunrise.app.presentation.sunrise

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.snackbar.Snackbar
import com.mfomin.sunrise.app.R
import com.mfomin.sunrise.app.databinding.FragmentSunriseBinding
import com.mfomin.sunrise.app.di.Injectable
import com.mfomin.sunrise.app.permission.PermissionManager
import com.mfomin.sunrise.app.util.livedata.liveDataResultHandler
import com.mfomin.sunrise.app.util.livedata.observe
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject


class SunriseFragment : DaggerFragment(), Injectable {

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SunriseViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(SunriseViewModel::class.java)
    }

    private lateinit var binding: FragmentSunriseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sunrise, container, false)

        binding.rbCurrentLocation.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.getSunriseInfo()
                binding.tvResult.text = ""
                binding.btnCitySelect.isEnabled = false
            }
        }

        binding.rbCustomCity.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.tvResult.text = ""
                binding.btnCitySelect.isEnabled = true
            }
        }

        binding.btnCitySelect.setOnClickListener {

        }

        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME))

        autocompleteFragment.setPlaceFields(
            Arrays.asList(
                Place.Field.NAME,
                Place.Field.LAT_LNG
            )
        )

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(status: Status) {

            }

            override fun onPlaceSelected(place: Place) {
                if (place.latLng == null || place.name == null) {
                    Snackbar.make(binding.root, R.string.error_cant_obtain_city_location, Snackbar.LENGTH_LONG)
                        .show()
                } else {
                    viewModel.getSunriseInfoForCity(
                        place.name ?: "",
                        place.latLng!!.latitude,
                        place.latLng!!.longitude
                    )
                }
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        permissionManager.checkAndRunPermissions(
            PERMISSION_REQUEST_CODE,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            {
                enableCurrentLocation(true)
            },
            {
                enableCurrentLocation(false)
                showLocationPermissionRequiredError()
            })
    }

    private fun enableCurrentLocation(isEnabled: Boolean) {
        binding.rbCurrentLocation.isEnabled = isEnabled
        binding.rbCurrentLocation.isChecked = isEnabled

        binding.rbCustomCity.isChecked = !isEnabled

        if (isEnabled) {
            subscribeToViewModel()
        }
    }

    private fun subscribeToViewModel() {
        viewModel.sunriseInfo.observe(this, liveDataResultHandler(
            onSuccess = {
                binding.tvResult.text = it.sunriseInfo.toString()
            },
            onProgress = {
                binding.tvResult.text = "loading..."
            },
            onError = {
                binding.tvResult.text = "Error"
            }
        ))
        viewModel.getSunriseInfo()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                enableCurrentLocation(true)
            } else {
                enableCurrentLocation(false)
                showLocationPermissionRequiredError()
            }
        }
    }

    private fun showLocationPermissionRequiredError() {
        Snackbar.make(this.view!!, R.string.label_need_permission, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.label_give_permission) {
                permissionManager.requestPermissions(
                    PERMISSION_REQUEST_CODE,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                )
            }.show()
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 2001
    }
}
