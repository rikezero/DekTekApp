package com.rikezero.dektek.domain.model

sealed class DekTeKResult<out T : Any> {
    data class Success<T : Any>(val data: T) : DekTeKResult<T>()
    data class Error(val exception: Exception) : DekTeKResult<Nothing>()

    inline fun onSuccess(action: (T) -> Unit): DekTeKResult<T> {
        if (this is Success) action(data)
        return this
    }

    inline fun onError(action: (Exception) -> Unit): DekTeKResult<T> {
        if (this is Error) action(exception)
        return this
    }
}
