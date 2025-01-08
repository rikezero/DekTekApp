package com.rikezero.dektek.ui.collections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.R
import com.rikezero.dektek.ui.foundation.components.InputText
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.makeItBold

@Composable
fun CardCollectionListBottomSheet(
    onCreateCollection: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var collectionName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        ProvideTextStyle(
            value = DekTekTheme.typography.h3.makeItBold()
        ) {
            Text(
                modifier = Modifier.wrapContentWidth(),
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
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true,
                imeAction = ImeAction.Done
            )
        )
        Spacer(
            modifier = Modifier
                .padding(
                    top = 24.dp
                )
        )
        Button() { }
    }
}

@Composable
@Preview(name = "CollectionBottomSheetPreview")
private fun CardCollectionBottomSheetPreview() {
    DekTekTheme {
        CardCollectionListBottomSheet()
    }
}