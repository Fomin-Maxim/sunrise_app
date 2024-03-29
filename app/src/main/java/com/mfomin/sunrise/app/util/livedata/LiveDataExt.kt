package com.mfomin.sunrise.app.util.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) =
    observe(owner, Observer<T> { v -> observer.invoke(v) })

fun <X, Y> LiveData<X>.map(transformer: (X) -> Y): LiveData<Y> =
    Transformations.map(this) { transformer(it) }
