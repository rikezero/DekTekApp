package com.rikezero.dektek.ui.collections

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.ui.foundation.components.collections.CardCollectionCell
import com.rikezero.dektek.ui.foundation.screen.ScreenComposable
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardCollectionsListScreen(
    navController: NavController,
    viewModel: CardCollectionListViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val collections = viewModel.cardCollectionsListState

    ScreenComposable(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //todo create a new collection
                }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = Icons.Filled.Add.name
                )
            }
        },
        content = {
            CardCollectionsListContent(
                collections = collections,
                onClick = { cardCollectionModel ->
                    //todo navigate to collection screen
                }
            )
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CardCollectionsListContent(
    collections: List<CardCollectionModel>,
    modifier: Modifier = Modifier,
    onClick: ((CardCollectionModel) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .imePadding()
            .imeNestedScroll()
    ) {
        items(collections){
            CardCollectionCell(
                collectionModel = it,
                onClick = onClick
            )
        }
    }
}