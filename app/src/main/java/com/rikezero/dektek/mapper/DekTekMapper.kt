package com.rikezero.dektek.mapper

import com.rikezero.dektek.domain.base.DekTekFailure
import com.rikezero.mtgapi_kotlin_sdk.domain.failure.MtgApiFailure

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
        is DekTekFailure.UnknownFailure ->
            MtgApiFailure.UnknownFailure(this.cause ?: Throwable(), this.message)
        else -> MtgApiFailure.UnknownFailure(this.cause ?: Throwable(), this.message)
    }
}