package com.mfomin.sunrise.app.permission

open class PermissionRequiredException : SecurityException {
    constructor() : super()
    constructor(msg: String) : super(msg)
}
