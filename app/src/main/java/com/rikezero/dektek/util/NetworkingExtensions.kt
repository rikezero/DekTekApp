package com.rikezero.dektek.util

import com.rikezero.dektek.domain.model.DekTekResult
import com.rikezero.dektek.networking.response.DekTeKResponse

fun <T> Result<T>.toDekTeKResponse(): DekTeKResponse<T> {
    return fold(
        onSuccess = { DekTeKResponse.Success(it) },
        onFailure = { DekTeKResponse.Error(it as Exception) }
    )
}

fun <T> DekTeKResponse<T>.toResult(): DekTekResult<T> = when (this) {
    is DekTeKResponse.Success -> DekTekResult.success(data)
    is DekTeKResponse.Error -> DekTekResult.failure(exception)
}

inline fun <T> result(
    block: () -> DekTeKResponse<T>
): DekTekResult<T> = runCatching {
    block().toResult()
}.getOrElse { error ->
    DekTekResult.failure(error)
}

suspend fun <T> resultSuspend(
    block: suspend () -> DekTeKResponse<T>
): DekTekResult<T> = runCatching {
    block().toResult()
}.getOrElse { error ->
    DekTekResult.failure(error)
}