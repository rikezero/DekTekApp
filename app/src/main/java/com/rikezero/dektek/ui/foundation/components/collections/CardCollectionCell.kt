package com.rikezero.dektek.ui.foundation.components.collections

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rikezero.dektek.R
import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.makeItBold

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardCollectionCell(
    collectionModel: CardCollectionModel,
    modifier: Modifier = Modifier,
    onClick: ((CardCollectionModel) -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RectangleShape,
        backgroundColor = DekTekTheme.colors.surface,
        onClick = {
            onClick?.invoke(collectionModel)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
            ) {
                ProvideTextStyle(
                    value = DekTekTheme.typography.body2.makeItBold()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 8.dp,
                                bottom = 4.dp
                            ),
                        text = collectionModel.collectionName,
                        color = DekTekTheme.colors.themeAwareTextColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 8.dp
                        )
                ) {
                    ProvideTextStyle(
                        value = DekTekTheme.typography.caption
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = stringResource(
                                R.string.collection_card_total_cards,
                                collectionModel.cards.size
                            ),
                            color = DekTekTheme.colors.themeAwareTextColor
                        )
                    }
                }
            }
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .padding(
                        start = 4.dp
                    ),
                imageVector = Icons.Filled.ChevronRight,
                tint = DekTekTheme.colors.primary,
                contentDescription = Icons.Filled.ChevronRight.name
            )
        }
    }
}

@Preview(name = "CardCollectionCellPreview")
@Preview(name = "CardCollectionCellPreview", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CardCollectionCellPreview(){
    val collections = listOf<CardCollectionModel>(
        CardCollectionModel(
            collectionName = "Collection 1",
            cards = mutableMapOf()
        ),
        CardCollectionModel(
            collectionName = "Collection 2",
            cards = mutableMapOf()
        ),
        CardCollectionModel(
            collectionName = "Collection 3",
            cards = mutableMapOf()
        ),
        CardCollectionModel(
            collectionName = "Collection 4",
            cards = mutableMapOf()
        )
    )

    DekTekTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(collections) {
                CardCollectionCell(it)
            }
        }
    }
}