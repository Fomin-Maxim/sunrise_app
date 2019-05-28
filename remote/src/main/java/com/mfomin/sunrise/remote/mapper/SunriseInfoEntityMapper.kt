package com.mfomin.sunrise.remote.mapper

import com.mfomin.sunrise.common.mapper.MapperFrom
import com.mfomin.sunrise.data.model.SunriseInfoEntity
import com.mfomin.sunrise.remote.model.RemoteSunriseInfo
import javax.inject.Inject

open class SunriseInfoEntityMapper @Inject constructor() : MapperFrom<RemoteSunriseInfo, SunriseInfoEntity> {
    override fun from(type: RemoteSunriseInfo) =
        SunriseInfoEntity(
            type.sunrise,
            type.sunset,
            type.solarNoon,
            type.dayLength,
            type.civilTwilightBegin,
            type.civilTwilightEnd,
            type.nauticalTwilightBegin,
            type.nauticalTwilightEnd,
            type.astronomicalTwilightBegin,
            type.astronomicalTwilightEnd
        )
}
