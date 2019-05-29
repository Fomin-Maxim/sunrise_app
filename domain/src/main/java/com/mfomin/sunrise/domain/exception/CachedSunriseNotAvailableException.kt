package com.mfomin.sunrise.domain.exception

import java.lang.RuntimeException

class CachedSunriseNotAvailableException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}