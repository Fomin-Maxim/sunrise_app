package com.mfomin.sunrise.common.mapper

interface MapperTo<out From, in To> {
    fun to(type: To): From
}