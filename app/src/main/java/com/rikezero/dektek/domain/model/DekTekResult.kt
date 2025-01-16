package com.rikezero.dektek.domain.model

import com.rikezero.dektek.domain.base.DekTekFailure

/**
 * A sealed class representing the result of an operation, which can either succeed or fail.
 *
 * @param R The type of the success value.
 */
open class DekTekResult<out R> @PublishedApi internal constructor(
    @PublishedApi
    internal val value: Any? = null
) {

    val isSuccess: Boolean = value !is Throwable

    val isFailure: Boolean = value is Throwable

    @Suppress("UNCHECKED_CAST")
    fun getOrNull(): R? = when {
        isFailure -> null
        else -> value as R
    }

    fun exceptionOrNull(): Throwable? = when {
        isFailure -> value as Throwable?
        else -> null
    }

    companion object {
        fun <T> success(value: T): DekTekResult<T> = DekTekResult(value)
        fun <T> failure(throwable: Throwable): DekTekResult<T> = DekTekResult(throwable)
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <T, R> DekTekResult<T>.map(
    transform: (value: T) -> R
): DekTekResult<R> = when {
    isSuccess -> DekTekResult.success(transform(value as T))
    else -> DekTekResult.failure(value as Throwable)
}

inline fun <T, R> DekTekResult<T?>.mapToNotNull(
    transform: (value: T) -> R
): DekTekResult<R> = this.map {
    it?.let(transform) ?: throw DekTekFailure.UnknownFailure(NullPointerException())
}

inline fun <reified T: Throwable, R> DekTekResult<R>.throwableMap(
    block: (T) -> Throwable
): DekTekResult<R> =
    runCatching {
        when (val exception = exceptionOrNull()){
            null -> this
            else -> if (exception is T) DekTekResult.failure(block(exception)) else this
        }
    }.getOrElse { error ->
        DekTekResult.failure(error)
    }

@Suppress("UNCHECKED_CAST")
inline fun <T> DekTekResult<T>.onSuccess(
    block: (T) -> Unit
): DekTekResult<T> {
    return if (isSuccess) {
        block(value as T)
        this
    } else {
        this
    }
}

inline fun <T> DekTekResult<T>.onFailure(
    block: (Throwable) -> Unit
): DekTekResult<T> {
    return if (isFailure) {
        block(value as Throwable)
        this
    } else {
        this
    }
}

fun <T> DekTekResult<T>.storageThrowableMap(): DekTekResult<T> =
    this.throwableMap<Throwable, T> {
        if (it !is DekTekFailure) DekTekFailure.DatabaseFailure(cause = it, error = it) else it
    }

inline fun <T : Any> DekTekResult<T?>.orDatabaseFailure(message: String): DekTekResult<T?> {
    return when {
        isSuccess && getOrNull() != null -> DekTekResult.success(getOrNull())
        isSuccess -> DekTekResult.failure(
            DekTekFailure.DatabaseFailure(
                cause = null,
                error = IllegalStateException(message)
            )
        )
        else -> this
    }
}
