package com.rikezero.dektek.ui.collections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.R
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.makeItBold

@Composable
fun CardCollectionListBottomSheet(
    modifier: Modifier = Modifier
) {
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
    }
}

@Composable
@Preview(name = "CollectionBottomSheetPreview")
private fun CardCollectionBottomSheetPreview(){
    DekTekTheme{
        CardCollectionListBottomSheet()
    }
}