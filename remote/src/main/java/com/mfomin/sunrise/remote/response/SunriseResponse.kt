package com.mfomin.sunrise.remote.response

import com.mfomin.sunrise.remote.model.RemoteSunriseInfo

data class SunriseResponse(val results: RemoteSunriseInfo, val status: String)