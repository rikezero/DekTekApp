package com.rikezero.dektek.mapper

import com.rikezero.dektek.domain.base.DekTekFailure
import com.rikezero.dektek.domain.model.DekTeKResult
import com.rikezero.dektek.networking.response.DekTeKResponse
import com.rikezero.mtgapi_kotlin_sdk.domain.failure.MtgApiFailure

fun <T : Any> DekTeKResponse<T>.toResult(): DekTeKResult<T> {
    return when (this) {
        is DekTeKResponse.Success -> DekTeKResult.Success(data)
        is DekTeKResponse.Error -> DekTeKResult.Error(exception)
    }
}

fun MtgApiFailure.toDekTekFailure(): DekTekFailure {
    return when (this) {
        is MtgApiFailure.UnknownFailure ->
            DekTekFailure.UnknownFailure(this.cause ?: Throwable(), this.message)
        is MtgApiFailure.NetworkingFailure ->
            DekTekFailure.NetworkingFailure(
                error = DekTekFailure.NetworkingFailure.Error(
                    code = this.error.code,
                    message = this.error.message
                ),
                httpCode = this.httpCode,
                message = this.message,
                cause = this.cause
            )
    }
}

fun DekTekFailure.toMtgApiFailure(): MtgApiFailure {
    return when (this) {
        is DekTekFailure.UnknownFailure ->
            MtgApiFailure.UnknownFailure(this.cause ?: Throwable(), this.message)
        is DekTekFailure.NetworkingFailure ->
            MtgApiFailure.NetworkingFailure(
                error = MtgApiFailure.NetworkingFailure.Error(
                    code = this.error.code,
                    message = this.error.message
                ),
                httpCode = this.httpCode,
                message = this.message,
                cause = this.cause
            )
    }
}