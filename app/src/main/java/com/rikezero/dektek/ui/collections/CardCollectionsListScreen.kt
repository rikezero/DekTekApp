package com.rikezero.dektek.ui.collections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rikezero.dektek.ui.foundation.screen.ScreenComposable
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardCollectionsListScreen(
    viewModel: CardCollectionListViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    ScreenComposable(
        modifier = modifier,
        content = { CardCollectionsListContent() }
    )
}

@Composable
private fun CardCollectionsListContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}