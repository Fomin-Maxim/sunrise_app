package com.mfomin.sunrise.domain.exception

class CachedSunriseNotAvailableException : Throwable {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}