package com.rikezero.dektek.ui.foundation.topbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.makeItBold

@Composable
fun TitleHeader(
    title: String,
    modifier: Modifier = Modifier,
    leftContent: @Composable (() -> Unit)? = null,
    rightContent: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    val titleContent: @Composable () -> Unit = @Composable {
        ProvideTextStyle(
            value = DekTekTheme.typography.h3.makeItBold()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                color = DekTekTheme.colors.themeAwareTextColor,
                textAlign = TextAlign.Center
            )
        }
    }

    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        contentPadding = PaddingValues(horizontal = 0.dp),
        content = {
            TopAppBarContent(
                title = titleContent,
                leftContent = leftContent,
                rightContent = rightContent
            )
        }
    )
}

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
) {
    Row {
        leftContent?.let {
            Row(
                Modifier
                    .height(56.dp),
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
                value = DekTekTheme.typography.h3.makeItBold()
            ) {
                title()
            }
        }
        CompositionLocalProvider(
            LocalContentColor provides DekTekTheme.colors.primary,
            LocalContentAlpha provides 1f
        ) {
            Row(
                Modifier.height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = rightContent
            )
        }
    }
}

@Preview(name = "TitleHeaderPreview")
@Preview(name = "TitleHeaderPreviewDark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TitleHeaderPreview(){
    DekTekTheme{
        TitleHeader(
            title = "Title Bar",
            leftContent = {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = DekTekTheme.colors.themeAwareTextColor,
                    contentDescription = null
                )
            },
            rightContent = {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(24.dp),
                    imageVector = Icons.Filled.Settings,
                    tint = DekTekTheme.colors.themeAwareTextColor,
                    contentDescription = null
                )
            }
        )
    }
}