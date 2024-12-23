package com.rikezero.dektek.ui.foundation.screen


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme

@Composable
fun DekTekModelBottomSheetLayout(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden),
    sheetShape: Shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    sheetElevation: Dp = 0.dp,
    sheetContentColor: Color = DekTekTheme.colors.black100,
    scrimColor: Color = DekTekTheme.colors.darkOpacity400,
    sheetBackgroundColor: Color = DekTekTheme.colors.white,
    content: @Composable () -> Unit
){
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetContent = sheetContent,
        sheetElevation = sheetElevation,
        sheetShape = sheetShape,
        sheetState = sheetState,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        scrimColor = scrimColor,
        content = content
    )
}