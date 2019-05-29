package com.mfomin.sunrise.data.mapper

import com.mfomin.sunrise.common.mapper.Mapper
import com.mfomin.sunrise.data.model.SunriseInfoEntity
import com.mfomin.sunrise.domain.model.SunriseInfo
import javax.inject.Inject

open class SunriseInfoMapper @Inject constructor() : Mapper<SunriseInfoEntity, SunriseInfo> {
    override fun from(type: SunriseInfoEntity) = SunriseInfo(
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

    override fun to(type: SunriseInfo) = SunriseInfoEntity(
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
