package com.mfomin.sunrise.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mfomin.sunrise.cache.room.SunriseDatabase

@Entity(tableName = SunriseDatabase.TableCitySunrise.NAME, indices = [Index(value = ["name"])])
data class CitySunriseCached(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var name: String?,
    var date: Long,
    @Embedded(prefix = "coordinates_")
    val coordinatesCached: CoordinatesCached,

    @Embedded(prefix = "sunriseInfo_")
    val sunriseInfoCached: SunriseInfoCached
)