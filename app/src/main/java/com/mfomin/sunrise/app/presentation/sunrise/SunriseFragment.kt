package com.mfomin.sunrise.app.presentation.sunrise

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.mfomin.sunrise.app.R
import com.mfomin.sunrise.app.databinding.FragmentSunriseBinding
import com.mfomin.sunrise.app.permission.PermissionManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SunriseFragment : DaggerFragment() {

    @Inject
    lateinit var permissionManager: PermissionManager

    lateinit var binding: FragmentSunriseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sunrise, container, false)
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
        }
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
