package com.rikezero.dektek.ui.foundation.screen

import android.view.View
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.findFragment
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme

@Composable
fun ScreenComposable(
    modifier: Modifier = Modifier,
    screenState: ScreenState = rememberScreenState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    sheetContent: (@Composable BottomSheetHandlerScope.() -> Unit)? = null,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerShape: Shape = MaterialTheme.shapes.large,
    statusBarColor: Color = Color.Transparent,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerGesturesEnabled: Boolean = true,
    enableSystemBarsPadding: Boolean = true,
    content: @Composable (PaddingValues) -> Unit
){
    val scaffoldChild = @Composable { childModifier: Modifier ->
        Scaffold(
            modifier = (if(enableSystemBarsPadding) childModifier.systemBarsPadding() else childModifier),
            scaffoldState = screenState.scaffoldState,
            topBar = {
                topBar()
            },
            bottomBar = {
                bottomBar()
            },
            snackbarHost = snackbarHost,
            drawerContent = drawerContent,
            drawerElevation = drawerElevation,
            drawerShape = drawerShape,
            drawerGesturesEnabled = drawerGesturesEnabled,
            content = content
        )
    }

    StatusBarConfig(statusBarColor)

    CompositionLocalProvider(
        LocalScreenState provides screenState,
        LocalScrollState provides screenState.scrollState
    ) {
        if (sheetContent != null) {
            DekTekModelBottomSheetLayout(
                modifier = if (enableSystemBarsPadding) Modifier.navigationBarsPadding() else Modifier,
                sheetContent = {
                    BottomSheetHandlerScope().run {
                        currentCaller = screenState.bottomSheetState.bottomSheetCaller.value
                        sheetContent()
                        if (!sheetHandled) {
                            Spacer(Modifier.size(10.dp))
                        }
                    }
                },
                sheetState = screenState.bottomSheetState.modalBottomSheetState,
                content = {
                    scaffoldChild(modifier)
                }
            )
        } else {
            scaffoldChild(modifier)
        }
    }
}

class BottomSheetHandlerScope {
    var currentCaller: Caller = InitialCaller
    var sheetHandled: Boolean = false

    @Composable
    inline fun <reified T : Caller> handleSheet(noinline sheet: @Composable (T) -> Unit) {
        if (currentCaller is T && currentCaller !is InitialCaller && !sheetHandled){
            sheet(currentCaller as T)
            sheetHandled = true
        }
    }
}

var LocalScreenState = staticCompositionLocalOf<ScreenState?> { null }

val LocalScrollState = staticCompositionLocalOf<ScrollableState> {
    error("No Scroll State provided")
}

@Composable
private fun StatusBarConfig(statusBarColor: Color){
    if (!(LocalInspectionMode.current || LocalView.current.isInsideBottomSheet())){
        val sysUiController = rememberSystemUiController()
        val isDarkModel = DekTekTheme.colors.isDark
        SideEffect {
            with(sysUiController){
                setStatusBarColor(statusBarColor)
                if (statusBarColor == Color.Transparent){
                    statusBarDarkContentEnabled = !isDarkModel
                }
            }
        }
    }
}

private fun View.isInsideBottomSheet() = runCatching {
    findFragment<BottomSheetDialogFragment>().let { true }
}.getOrElse { false }