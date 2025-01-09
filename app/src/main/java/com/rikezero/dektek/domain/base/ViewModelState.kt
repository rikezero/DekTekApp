package com.rikezero.dektek.domain.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

sealed interface ViewModelState<out T> {
    data object Loading : ViewModelState<Nothing>
    data class Success<out R>(val data: R) : ViewModelState<R>
    data class Error(val error: Throwable) : ViewModelState<Nothing>
}

fun <R> ViewModelState<R>.getSuccessData(): R? = (this as? ViewModelState.Success<R>)?.data

fun <T> ViewModelState<T>.getNetworkingFailure(): DekTekFailure? =
    ((this as? ViewModelState.Error)?.error as? DekTekFailure.NetworkingFailure)

fun <T> ViewModelState<T>.getResolvedContent(
    onLoading: @Composable ((PaddingValues) -> Unit)? = null,
    onSuccess: @Composable ((PaddingValues) -> Unit)? = null,
    onError: @Composable ((PaddingValues) -> Unit)? = null,
): @Composable ((PaddingValues) -> Unit)? {
    return when(this){
        is ViewModelState.Loading -> { paddingValues ->
            onLoading?.invoke(paddingValues)
        }

        is ViewModelState.Error -> { paddingValues ->
            onError?.invoke(paddingValues)
        }
        is ViewModelState.Success -> { paddingValues ->
            onSuccess?.invoke(paddingValues)
        }
    }
}