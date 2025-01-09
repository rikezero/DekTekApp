package com.rikezero.dektek.ui.foundation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.ui.foundation.components.ButtonType.PRIMARY
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.makeItBold
import com.rikezero.dektek.util.thenIf

@Composable
fun SmartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    buttonType: ButtonType = PRIMARY,
    content: @Composable RowScope.() -> Unit
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        loading = loading,
        buttonType = buttonType,
        content = content
    )
}

@Composable
fun SmartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonType: ButtonType = PRIMARY,
    content: @Composable RowScope.() -> Unit
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        buttonType = buttonType,
        content = content
    )
}

@Composable
fun SmartButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonType: ButtonType = PRIMARY,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        buttonType = buttonType
    ) {
        Text(text = text)
    }
}

@Composable
fun SmartButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    buttonType: ButtonType = PRIMARY,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        loading = loading,
        buttonType = buttonType
    ) {
        Text(text = text)
    }
}

@Composable
fun SmartRightIconButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.ChevronRight,
    isCenteredIcon: Boolean = false,
    enabled: Boolean = true,
    loading: Boolean = false,
    buttonType: ButtonType = PRIMARY
) {
    BaseButton(
        modifier = modifier.width(IntrinsicSize.Max),
        onClick = onClick,
        enabled = enabled,
        loading = loading,
        buttonType = buttonType,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Text(
            modifier = Modifier.weight(1F),
            text = text,
            textAlign = TextAlign.Start
        )
        Spacer(
            modifier = Modifier.thenIf(
                condition = isCenteredIcon,
                ifTrue = { width(8.dp) },
                ifFalse = { width(16.dp) }
            )
        )
        Icon(
            modifier = Modifier
                .size(24.dp)
                .semantics { contentDescription = icon.name },
            imageVector = icon,
            contentDescription = icon.name
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    buttonType: ButtonType = PRIMARY,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
    content: @Composable RowScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false
    ) {
        val buttonContent: @Composable RowScope.() -> Unit = {
            ProvideTextStyle(value = DekTekTheme.typography.subtitle2.makeItBold()) {
                if (loading) CircularProgressIndicator(
                    modifier = Modifier
                        .semantics {
                            contentDescription = "Loading Indicator"
                        }
                        .size(16.dp),
                    strokeWidth = 2.dp,
                    color = DekTekTheme.colors.white
                )
                else content()
            }
        }
        when (buttonType) {
            PRIMARY -> Button(
                onClick = onClick.takeIf { !loading } ?: {},
                modifier = modifier.defaultMinSize(minWidth = 160.dp, minHeight = 48.dp),
                enabled = enabled,
                colors = buttonType.buttonColorsForType(),
                elevation = null,
                shape = DekTekTheme.shapes.medium,
                contentPadding = contentPadding,
                content = buttonContent
            )

            ButtonType.SECONDARY -> OutlinedButton(
                onClick = onClick.takeIf { !loading } ?: {},
                modifier = modifier.defaultMinSize(minWidth = 160.dp, minHeight = 48.dp),
                enabled = enabled,
                colors = buttonType.buttonColorsForType(),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (enabled) DekTekTheme.colors.celestialBlue300 else DekTekTheme.colors.white300
                ),
                elevation = null,
                shape = DekTekTheme.shapes.medium,
                contentPadding = contentPadding,
                content = buttonContent
            )

            ButtonType.TERTIARY -> TextButton(
                onClick = onClick.takeIf { !loading } ?: {},
                modifier = modifier.defaultMinSize(minWidth = 160.dp, minHeight = 48.dp),
                enabled = enabled,
                colors = buttonType.buttonColorsForType(),
                shape = DekTekTheme.shapes.medium,
                contentPadding = contentPadding,
                content = buttonContent
            )
        }

    }
}

enum class ButtonType {
    PRIMARY,
    SECONDARY,
    TERTIARY
}

@Composable
private fun ButtonType.buttonColorsForType() = when (this) {
    PRIMARY -> ButtonDefaults.buttonColors(
        backgroundColor = DekTekTheme.colors.secondary,
        contentColor = DekTekTheme.colors.themeAwareTextColor,
        disabledContentColor = DekTekTheme.colors.white400,
        disabledBackgroundColor = DekTekTheme.colors.black300
    )

    ButtonType.SECONDARY -> ButtonDefaults.buttonColors(
        backgroundColor = Color.Transparent,
        contentColor = DekTekTheme.colors.secondary,
        disabledContentColor = DekTekTheme.colors.white400
    )

    ButtonType.TERTIARY -> ButtonDefaults.buttonColors(
        contentColor = DekTekTheme.colors.themeAwareTextColor,
        disabledContentColor = DekTekTheme.colors.white400
    )
}