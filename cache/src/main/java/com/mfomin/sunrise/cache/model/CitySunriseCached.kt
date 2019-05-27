package com.mfomin.sunrise.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mfomin.sunrise.cache.room.SunriseDatabase

@Entity(tableName = SunriseDatabase.TableCitySunrise.NAME)
data class CitySunriseCached(
    var name: String,

    @Embedded(prefix = "coordinates_")
    val coordinatesCached: CoordinatesCached,

    @Embedded(prefix = "sunriseInfo_")
    val sunriseInfoCached: SunriseInfoCached
) {
    @PrimaryKey
    var id: Int = -1
    var date: Long = 0
}