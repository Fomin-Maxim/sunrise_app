package com.mfomin.sunrise.cache.mapper

import com.mfomin.sunrise.cache.model.SunriseInfoCached
import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.SunriseInfoEntity
import javax.inject.Inject

open class SunriseInfoCachedEntityMapper @Inject constructor() : Mapper<SunriseInfoCached, SunriseInfoEntity> {
    override fun from(type: SunriseInfoCached) = SunriseInfoEntity(
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

    override fun to(type: SunriseInfoEntity) = SunriseInfoCached(
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

