package com.rikezero.dektek.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rikezero.dektek.domain.base.ViewModelState
import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.mtgapi_kotlin_sdk.domain.model.lists.CardListModel
import com.rikezero.mtgapi_kotlin_sdk.domain.result.onFailure
import com.rikezero.mtgapi_kotlin_sdk.domain.result.onSuccess
import com.rikezero.mtgapi_kotlin_sdk.domain.usecase.GetCardsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardCollectionListViewModel(
    val getCardsUseCase: GetCardsUseCase,
    val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _cardCollectionsListState: MutableStateFlow<ViewModelState<List<CardCollectionModel>>> = MutableStateFlow(ViewModelState.Loading)
    val cardCollectionsListState: StateFlow<ViewModelState<List<CardCollectionModel>>> = _cardCollectionsListState.asStateFlow()

    private val _searchCardState: MutableStateFlow<ViewModelState<CardListModel>> = MutableStateFlow(ViewModelState.Loading)
    val searchCardState: StateFlow<ViewModelState<CardListModel>> = _searchCardState.asStateFlow()

    fun searchCard(
        cardName: String? = null,
        set: String? = null,
        setName: String? = null,
        cmc: Double? = null,
        gameFormat: String? = null,
        type: String? = null,
        types: List<String>? = null,
        subTypes: List<String>? = null,
        superTypes: List<String>? = null
    ){
        viewModelScope.launch(dispatcher) {
            _searchCardState.update { ViewModelState.Loading }
            getCardsUseCase.invoke(
                GetCardsUseCase.GetCardsParams(
                    name = cardName,
                    set = set,
                    setName = setName,
                    cmc = cmc,
                    gameFormat = gameFormat,
                    type = type,
                    types = types,
                    subtypes = subTypes,
                    supertypes = superTypes
                )
            ).onSuccess { cardListModel ->
                _searchCardState.update { ViewModelState.Success(cardListModel) }
            }.onFailure { error ->
                _searchCardState.update { ViewModelState.Error(error) }
            }
        }
    }
}