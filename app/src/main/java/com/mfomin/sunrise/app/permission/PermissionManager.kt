package com.mfomin.sunrise.app.permission

interface PermissionManager {
    fun checkAndRunPermissions(
        requestCode: Int,
        permissions: Array<String>,
        onPermissionGranted: (Array<String>) -> Unit,
        onShowRationale: (Array<String>) -> Unit
    )

    fun hasPermissions(permissions: Array<String>): Boolean

    fun requestPermissions(requestCode: Int, permissions: Array<String>)

    fun runPermissions(
        requestCode: Int,
        permissions: Array<String>,
        onShowRationale: (Array<String>) -> Unit
    )

    fun shouldShowRequestPermissionRationale(permissions: Array<String>): Boolean

    fun runIfVerifiedOrShowRationale(
        permissions: Array<String>,
        grantResults: IntArray,
        onPermissionsGranted: (Array<String>) -> Unit,
        onPermissionsDenied: (Array<String>) -> Unit,
        onPermissionsNeverAskAgain: (Array<String>) -> Unit
    )

    fun runIfVerifiedOrShowRationaleOnMatchRequestCode(
        checkRequestCode: Int,
        permissionRequestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
        onPermissionsGranted: (Array<String>) -> Unit,
        onPermissionsDenied: (Array<String>) -> Unit,
        onPermissionsNeverAskAgain: (Array<String>) -> Unit
    )

    fun verifyPermissions(grantResults: IntArray): Boolean
}