package com.mfomin.sunrise.app.util.livedata

fun <T> liveDataResultHandler(
        onSuccess: (T) -> Unit,
        onProgress: () -> Unit = {},
        onError: (Throwable) -> Unit = defaultErrorHandler()
): ((Result<T>?) -> Unit) = {
    when (it) {
        is Result.Success ->
            onSuccess(it.data)

        is Result.InProgress ->
            onProgress()

        is Result.Failure ->
            onError(it.e)
    }
}