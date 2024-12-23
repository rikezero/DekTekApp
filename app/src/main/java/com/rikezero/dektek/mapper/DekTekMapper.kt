package com.rikezero.dektek.mapper

import com.rikezero.dektek.domain.model.DekTeKResult
import com.rikezero.dektek.networking.response.DekTeKResponse

fun <T : Any> DekTeKResponse<T>.toResult(): DekTeKResult<T> {
    return when (this) {
        is DekTeKResponse.Success -> DekTeKResult.Success(data)
        is DekTeKResponse.Error -> DekTeKResult.Error(exception)
    }
}