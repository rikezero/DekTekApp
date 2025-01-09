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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rikezero.dektek.R
import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.ui.foundation.components.collections.CardCollectionCell
import com.rikezero.dektek.ui.foundation.screen.DefaultCaller
import com.rikezero.dektek.ui.foundation.screen.LocalScreenState
import com.rikezero.dektek.ui.foundation.screen.ScreenComposable
import com.rikezero.dektek.ui.foundation.screen.rememberScreenState
import com.rikezero.dektek.ui.foundation.topbar.TitleHeader
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardCollectionsListScreen(
    navController: NavController,
    viewModel: CardCollectionListViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val collections = viewModel.cardCollectionsListState
    val screenState = rememberScreenState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    ScreenComposable(
        modifier = modifier,
        screenState = screenState,
        topBar = {
            TitleHeader(
                title = stringResource(R.string.collection_card_screen_title)
            )
        },
        sheetContent = {
            handleSheet<DefaultCaller> {
                CardCollectionListBottomSheet(
                    onCreateCollection = { collectionName ->
                        keyboardController?.hide()
                        coroutineScope.launch {
                            screenState.bottomSheetState.modalBottomSheetState.hide()
                            viewModel.createCollection(collectionName = collectionName)
                        }
                    },
                    sheetState = screenState.bottomSheetState.modalBottomSheetState,
                    onDismissStateCallback = {
                        keyboardController?.hide()
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        screenState.bottomSheetState.show()
                    }
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