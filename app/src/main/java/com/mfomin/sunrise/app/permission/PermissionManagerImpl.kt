package com.mfomin.sunrise.app.permission

import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import javax.inject.Inject

open class PermissionManagerImpl @Inject constructor(private val fragment: Fragment) : PermissionManager {

    override fun checkAndRunPermissions(
        requestCode: Int,
        permissions: Array<String>,
        onPermissionGranted: (Array<String>) -> Unit,
        onShowRationale: (Array<String>) -> Unit
    ) {
        if (hasPermissions(permissions)) {
            onPermissionGranted(permissions)
            return
        }

        if (!shouldShowRequestPermissionRationale(permissions)) {
            requestPermissions(requestCode, permissions)
            return
        }

        onShowRationale(permissions)
    }

    override fun hasPermissions(permissions: Array<String>) =
        permissions.all {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ContextCompat.checkSelfPermission(
                    this.fragment.requireContext(),
                    it
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        }

    override fun requestPermissions(requestCode: Int, permissions: Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.fragment.requestPermissions(permissions, requestCode)
        }
    }

    override fun runPermissions(
        requestCode: Int,
        permissions: Array<String>,
        onShowRationale: (Array<String>) -> Unit
    ) {
        if (!shouldShowRequestPermissionRationale(permissions)) {
            requestPermissions(requestCode, permissions)

            return
        }

        onShowRationale(permissions)
    }

    override fun shouldShowRequestPermissionRationale(permissions: Array<String>) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions.any { fragment.shouldShowRequestPermissionRationale(it) }
        } else {
            false
        }


    override fun runIfVerifiedOrShowRationale(
        permissions: Array<String>,
        grantResults: IntArray,
        onPermissionsGranted: (Array<String>) -> Unit,
        onPermissionsDenied: (Array<String>) -> Unit,
        onPermissionsNeverAskAgain: (Array<String>) -> Unit
    ) {
        if (verifyPermissions(grantResults)) {
            onPermissionsGranted(permissions)

            return
        }

        if (!shouldShowRequestPermissionRationale(permissions)) {
            onPermissionsNeverAskAgain(permissions)

            return
        }

        onPermissionsDenied(permissions)
    }

    override fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
        checkRequestCode: Int,
        permissionRequestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
        onPermissionsGranted: (Array<String>) -> Unit,
        onPermissionsDenied: (Array<String>) -> Unit,
        onPermissionsNeverAskAgain: (Array<String>) -> Unit
    ) {
        when (checkRequestCode) {
            permissionRequestCode -> runIfVerifiedOrShowRationale(
                permissions,
                grantResults,
                onPermissionsGranted,
                onPermissionsDenied,
                onPermissionsNeverAskAgain
            )
        }
    }

    override fun verifyPermissions(grantResults: IntArray) =
        grantResults.none { it != PackageManager.PERMISSION_GRANTED }
}