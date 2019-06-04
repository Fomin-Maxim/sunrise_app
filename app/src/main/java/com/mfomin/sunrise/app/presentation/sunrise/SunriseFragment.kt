package com.mfomin.sunrise.app.presentation.sunrise

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.snackbar.Snackbar
import com.mfomin.sunrise.app.R
import com.mfomin.sunrise.app.databinding.FragmentSunriseBinding
import com.mfomin.sunrise.app.di.Injectable
import com.mfomin.sunrise.app.permission.PermissionManager
import com.mfomin.sunrise.app.presentation.common.BaseFragment
import com.mfomin.sunrise.app.util.livedata.liveDataResultHandler
import com.mfomin.sunrise.app.util.livedata.observe
import com.mfomin.sunrise.app.util.setVisibility
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class SunriseFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SunriseViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(SunriseViewModel::class.java)
    }

    private var autocompleteFragment: AutocompleteSupportFragment? = null
    private lateinit var internetAvailabilitySnackbar: Snackbar

    private lateinit var binding: FragmentSunriseBinding
    private val dateFormat = DateFormat.getTimeInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sunrise, container, false)

        autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        setupAutocompleteFragment()
        binding.viewModel = viewModel

        subscribeToViewModel()

        binding.rgRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            enableCustomCity(checkedId == R.id.rb_custom_city)
        }

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

        internetAvailabilitySnackbar =
            Snackbar.make(binding.root, R.string.error_no_internet, Snackbar.LENGTH_INDEFINITE)
    }

    private fun enableCurrentLocation(isEnabled: Boolean) {
        binding.rbCurrentLocation.isEnabled = isEnabled
        binding.rbCurrentLocation.isChecked = isEnabled

        binding.rbCustomCity.isChecked = !isEnabled
    }

    private fun enableCustomCity(isEnabled: Boolean) {
        binding.cvAutocomplete.setVisibility(isEnabled)
    }

    private fun subscribeToViewModel() {
        viewModel.sunriseInfo.observe(this, liveDataResultHandler(
            onSuccess = {
                binding.tvResult.text =
                    getString(
                        R.string.sunrise_result,
                        dateFormat.format(it.sunriseInfo.sunrise),
                        dateFormat.format(it.sunriseInfo.sunset),
                        dateFormat.format(it.sunriseInfo.solarNoon),
                        DateUtils.formatElapsedTime(it.sunriseInfo.dayLength)
                    )
            },
            onCreated = {
                binding.tvResult.text = ""
            },
            onProgress = {
                binding.tvResult.text = getString(R.string.label_loading)
            },
            onError = {
                binding.tvResult.text = getString(R.string.label_unknown_error)
            }
        ))

        viewModel.networkConnected.observe(this, androidx.lifecycle.Observer { isConnected ->
            if (isConnected) {
                internetAvailabilitySnackbar.dismiss()
            } else {
                internetAvailabilitySnackbar.show()
            }
        })

        viewModel.selectedPlace.observe(this) { selectedPlace ->
            if (selectedPlace?.place == null) {
                binding.tvResult.text = ""
            }
        }
    }

    private fun setupAutocompleteFragment() {
        autocompleteFragment?.apply {
            setHint(getString(R.string.label_select_city))

            view?.findViewById<View>(R.id.places_autocomplete_clear_button)
                ?.setOnClickListener {
                    autocompleteFragment?.setText("")
                    binding.tvResult.text = ""
                    viewModel.clearSelectedPlace()
                }

            setPlaceFields(
                Arrays.asList(
                    Place.Field.ID, Place.Field.NAME
                )
            )

            setPlaceFields(
                Arrays.asList(
                    Place.Field.NAME, Place.Field.LAT_LNG
                )
            )

            setOnPlaceSelectedListener(object : PlaceSelectionListener {
                override fun onPlaceSelected(place: Place) {
                    if (place.latLng == null || place.name == null) {
                        Snackbar.make(binding.root, R.string.error_cant_obtain_city_location, Snackbar.LENGTH_LONG)
                            .show()
                    } else {
                        viewModel.setSelectedPlace(SelectedPlace(place))
                    }
                }

                override fun onError(status: Status) {}
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                enableCurrentLocation(true)
                viewModel.updateSunrise()
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
