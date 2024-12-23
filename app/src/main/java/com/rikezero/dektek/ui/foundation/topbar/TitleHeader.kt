package com.rikezero.dektek.ui.foundation.topbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme

@Composable
fun TitleHeader(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    leftContent: @Composable (() -> Unit)? = null,
    rightContent: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        contentPadding = PaddingValues(horizontal = 0.dp),
        content = {
            TopAppBarContent(
                title = title,
                leftContent = leftContent,
                rightContent = rightContent
            )
        }
    )
}

@Composable
private fun TopAppBarContent(
    title: @Composable () -> Unit,
    leftContent: @Composable (() -> Unit)? = null,
    rightContent: @Composable RowScope.() -> Unit = {}
){
    Row {
        leftContent?.let {
            Row(
                Modifier
                    .height(56.dp)
                    .width(72.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides DekTekTheme.colors.primary,
                    LocalContentAlpha provides 1f,
                    content = it
                )
            }
        }
        Row(
            Modifier
                .height(56.dp)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProvideTextStyle(
                value = DekTekTheme.typography.h3.copy(fontWeight = FontWeight.Bold)
            ) {
                title()
            }
        }
        CompositionLocalProvider(
            LocalContentColor provides DekTekTheme.colors.primary,
            LocalContentAlpha provides 1f
        ){
            Row(
                Modifier.height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = rightContent
            )
        }
    }
}