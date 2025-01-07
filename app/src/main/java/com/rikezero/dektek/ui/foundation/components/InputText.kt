package com.rikezero.dektek.ui.foundation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.ui.foundation.components.InputTextState.DEFAULT
import com.rikezero.dektek.ui.foundation.components.InputTextState.DISABLED
import com.rikezero.dektek.ui.foundation.components.InputTextState.ERROR
import com.rikezero.dektek.ui.foundation.components.InputTextState.SUCCESS
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.makeItBold
import com.rikezero.dektek.util.thenIf

@Composable
fun InputText(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    state: InputTextState = DEFAULT,
    readOnly: Boolean = false,
    placeholder: String? = null,
    topLabel: @Composable (() -> Unit)? = null,
    bottomLabel: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null,
    leftIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    fixedHeight: Boolean = true,
    defaultHeight: Int = 48,
    maxLength: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE
) {
    DekTekTheme{
        Column(
            modifier = modifier
        ) {
            if (topLabel != null){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides state.toTopLabelContentColor()
                    ) {
                        ProvideTextStyle(
                            value = DekTekTheme.typography.h6.copy(color = state.toTopLabelContentColor())
                        ) {
                            topLabel()
                        }
                    }
                }
            }
            TextField(
                value = value,
                onValueChange = {
                    onValueChange(if (it.text.length > maxLength) it.copy(text = it.text.take(maxLength)) else it)
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = TextFieldDefaults.MinWidth)
                    .thenIf(
                        condition = fixedHeight,
                        ifTrue = { height(defaultHeight.dp) },
                        ifFalse = { defaultMinSize(minHeight = defaultHeight.dp) }
                    ),
                enabled = state != DISABLED,
                readOnly = readOnly,
                textStyle = DekTekTheme.typography.h6.makeItBold(),
                placeholder = placeholder?.let {
                    @Composable {
                        Text(
                            text = placeholder,
                            style = DekTekTheme.typography.h6.makeItBold()
                        )
                    }
                },
                leadingIcon = leftIcon,
                trailingIcon = rightIcon,
                isError = state == ERROR,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                shape = DekTekTheme.shapes.medium,
                colors = state.toTextFieldColors()
            )
            if (bottomLabel != null){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides state.toBottomLabelContentColor()
                    ) {
                        ProvideTextStyle(
                            value = DekTekTheme.typography.h6.copy(color = state.toBottomLabelContentColor())
                        ) {
                            bottomLabel()
                        }
                    }
                }
            }
        }
    }

}

enum class InputTextState {
    DEFAULT,
    ERROR,
    SUCCESS,
    DISABLED
}

@Composable
private fun InputTextState.toTopLabelContentColor() = when (this) {
    DISABLED -> DekTekTheme.colors.black500
    else -> DekTekTheme.colors.secondary
}

@Composable
private fun InputTextState.toBottomLabelContentColor() = when (this) {
    DISABLED -> DekTekTheme.colors.black500
    ERROR -> DekTekTheme.colors.error200
    SUCCESS -> DekTekTheme.colors.success200
    DEFAULT -> DekTekTheme.colors.secondary
}

@Composable
private fun InputTextState.toTextFieldColors(): TextFieldColors =
    TextFieldDefaults.textFieldColors(
        textColor = when (this) {
            DEFAULT -> DekTekTheme.colors.themeAwareTextColor
            ERROR -> DekTekTheme.colors.error200
            SUCCESS -> DekTekTheme.colors.success200
            DISABLED -> DekTekTheme.colors.black500
        },
        disabledTextColor = DekTekTheme.colors.black500,
        backgroundColor = when (this) {
            DEFAULT -> DekTekTheme.colors.surface
            ERROR -> DekTekTheme.colors.error500
            SUCCESS -> DekTekTheme.colors.success500
            DISABLED -> DekTekTheme.colors.white300
        },
        cursorColor = DekTekTheme.colors.secondary,
        errorCursorColor = DekTekTheme.colors.secondary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        leadingIconColor = when (this) {
            DEFAULT -> DekTekTheme.colors.secondary
            ERROR -> DekTekTheme.colors.error200
            SUCCESS -> DekTekTheme.colors.success200
            DISABLED -> DekTekTheme.colors.black500
        },
        disabledLeadingIconColor = DekTekTheme.colors.black500,
        errorLeadingIconColor = DekTekTheme.colors.error200,
        trailingIconColor = when (this) {
            DEFAULT -> DekTekTheme.colors.secondary
            ERROR -> DekTekTheme.colors.error200
            SUCCESS -> DekTekTheme.colors.success200
            DISABLED -> DekTekTheme.colors.black500
        },
        disabledTrailingIconColor = DekTekTheme.colors.black500,
        errorTrailingIconColor = DekTekTheme.colors.error200,
        focusedLabelColor = DekTekTheme.colors.white300,
        unfocusedLabelColor = DekTekTheme.colors.black500,
        errorLabelColor = DekTekTheme.colors.error200,
        placeholderColor = when (this) {
            DEFAULT -> DekTekTheme.colors.black500
            ERROR -> DekTekTheme.colors.error200
            SUCCESS -> DekTekTheme.colors.success200
            DISABLED -> DekTekTheme.colors.black500
        },
        disabledPlaceholderColor = DekTekTheme.colors.black500
    )