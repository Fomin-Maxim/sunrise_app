package com.mfomin.sunrise.common.mapper

interface MapperFrom<in From, out To> {
    fun from(type: From): To
}