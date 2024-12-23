package com.rikezero.dektek.ui.foundation.screen

import android.os.Parcelable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.android.parcel.Parcelize

interface Caller : Parcelable

@Parcelize
object DefaultCaller: Caller

/**
 * This caller is used internally to set initial state
 * DO NOT USE IT
 */
@Parcelize
internal object InitialCaller : Caller

@Stable
class BottomSheetState(
    val modalBottomSheetState: ModalBottomSheetState,
    val bottomSheetCaller: MutableState<Caller>
) {
    suspend fun show(caller: Caller = DefaultCaller){
        bottomSheetCaller.value = caller
        modalBottomSheetState.show()
    }

    suspend fun hide(){
        modalBottomSheetState.hide()
    }
}

@Stable
class ScreenState(
    val scaffoldState: ScaffoldState,
    val bottomSheetState: BottomSheetState,
    val scrollState: ScrollState,
    val lazyListState: LazyListState,
    val lazyGridState: LazyGridState
)

@Composable
fun rememberBottomSheetState(
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
): BottomSheetState {
    val bottomSheetControllerState = rememberSaveable { mutableStateOf<Caller>(InitialCaller) }
    return remember(sheetState, bottomSheetControllerState) {
        BottomSheetState(
            sheetState,
            bottomSheetControllerState
        )
    }
}

@Composable
fun rememberScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    sheetState: BottomSheetState = rememberBottomSheetState(),
    scrollState: ScrollState = rememberScrollState(),
    lazyListState: LazyListState = rememberLazyListState(),
    lazyGridState: LazyGridState = rememberLazyGridState()
): ScreenState {
    return remember {
        ScreenState(
            scaffoldState = scaffoldState,
            bottomSheetState = sheetState,
            scrollState = scrollState,
            lazyListState = lazyListState,
            lazyGridState = lazyGridState
        )
    }
}