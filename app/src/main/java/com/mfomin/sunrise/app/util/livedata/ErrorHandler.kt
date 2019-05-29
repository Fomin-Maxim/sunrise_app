package com.mfomin.sunrise.app.util.livedata

import android.util.Log

fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Log.e("ErrorHandler", e.localizedMessage, e) }
