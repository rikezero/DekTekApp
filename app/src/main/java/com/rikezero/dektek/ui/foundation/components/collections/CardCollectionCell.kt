package com.rikezero.dektek.ui.foundation.components.collections

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme

@Composable
fun CardCollectionCell(
    collectionModel: CardCollectionModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RectangleShape,
    ) { }
}

@Composable
private fun CardCollectionCellPreview(){
    val collections = listOf<CardCollectionModel>()

    DekTekTheme {
        LazyColumn {
            items(collections) {
                CardCollectionCell(it)
            }
        }
    }
}