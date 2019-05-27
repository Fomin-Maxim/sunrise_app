package com.mfomin.sunrise.app.util

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T
}