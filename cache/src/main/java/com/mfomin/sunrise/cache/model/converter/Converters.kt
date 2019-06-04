package com.mfomin.sunrise.cache.model.converter


import androidx.room.TypeConverter
import java.util.Date


object Converters {
    @JvmStatic
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @JvmStatic
    @TypeConverter
    fun toTimestamp(date: Date?): Long {
        return (date?.time) ?: 0
    }
}
