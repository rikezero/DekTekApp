package com.rikezero.dektek.ui.collections

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.R
import com.rikezero.dektek.ui.foundation.components.InputText
import com.rikezero.dektek.ui.foundation.components.InputTextState
import com.rikezero.dektek.ui.foundation.components.SmartButton
import com.rikezero.dektek.ui.foundation.screen.rememberScreenState
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.makeItBold

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCollectionListBottomSheet(
    onCreateCollection: (String) -> Unit,
    sheetState: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    onDismissStateCallback: (() -> Unit)? = null
) {

    var collectionName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }
    var inputState by rememberSaveable { mutableStateOf(InputTextState.DEFAULT) }

    if (sheetState.currentValue != ModalBottomSheetValue.Hidden && onDismissStateCallback != null) {
        DisposableEffect(Unit) {
            onDispose {
                onDismissStateCallback()
            }
        }
    }
    LaunchedEffect(Unit) {
        snapshotFlow { sheetState.currentValue }
            .collect {
                if (it == ModalBottomSheetValue.Hidden) collectionName = TextFieldValue()
            }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .imePadding()
            .imeNestedScroll()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(
                        width = 100.dp,
                        height = 6.dp
                    )
                    .background(
                        color = DekTekTheme.colors.white500,
                        shape = DekTekTheme.shapes.large
                    )
            )
        }
        ProvideTextStyle(
            value = DekTekTheme.typography.h3.makeItBold()
        ) {
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 16.dp),
                text = stringResource(R.string.collection_card_bottomsheet_title),
                color = DekTekTheme.colors.themeAwareTextColor
            )
        }

        InputText(
            modifier = Modifier
                .padding(top = 24.dp),
            value = collectionName,
            onValueChange = { newValue ->
                collectionName = newValue
            },
            state = inputState,
            singleLine = true,
            placeholder = stringResource(R.string.collection_card_bottomsheet_input_placeholder),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true,
                imeAction = ImeAction.Done
            )
        )
        Spacer(
            modifier = Modifier
                .height(48.dp)
        )
        SmartButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                collectionName.text.takeIf { it.isNotEmpty() }?.let {
                    inputState = InputTextState.SUCCESS
                    onCreateCollection.invoke(it)
                } ?: run {
                    inputState = InputTextState.ERROR
                }

            },
            text = stringResource(R.string.collection_card_bottomsheet_button)
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
    }
}

@Composable
@Preview(
    name = "CollectionBottomSheetPreview",
    showBackground = true
)
@Preview(
    name = "CollectionBottomSheetPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
private fun CardCollectionBottomSheetPreview() {
    val state = rememberScreenState()
    DekTekTheme {
        CardCollectionListBottomSheet(
            onCreateCollection = {},
            sheetState = state.bottomSheetState.modalBottomSheetState
        )
    }
}