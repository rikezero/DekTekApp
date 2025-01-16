package com.rikezero.dektek.ui.collections.collectionslist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rikezero.dektek.domain.base.ViewModelState
import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.domain.model.onFailure
import com.rikezero.dektek.domain.model.onSuccess
import com.rikezero.dektek.domain.usecase.CreateCollectionUseCase
import com.rikezero.dektek.domain.usecase.DeleteCollectionUseCase
import com.rikezero.dektek.domain.usecase.GetCollectionByNameUseCase
import com.rikezero.dektek.domain.usecase.GetCollectionsUseCase
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
    private val getCardsUseCase: GetCardsUseCase,
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val getCollectionsUseCase: GetCollectionsUseCase,
    private val getCollectionByNameUseCase: GetCollectionByNameUseCase,
    private val deleteCollectionUseCase: DeleteCollectionUseCase,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _cardCollectionsListState: MutableList<CardCollectionModel> = mutableStateListOf()
    val cardCollectionsListState: List<CardCollectionModel> get() = _cardCollectionsListState

    private val _searchCardState: MutableStateFlow<ViewModelState<CardListModel>> = MutableStateFlow(ViewModelState.Loading)
    val searchCardState: StateFlow<ViewModelState<CardListModel>> = _searchCardState.asStateFlow()

    fun createCollection(collectionName: String){
        val collection = CardCollectionModel(collectionName = collectionName)
        viewModelScope.launch(dispatcher) {
            createCollectionUseCase.invoke(collection)
                .onSuccess {
                    _cardCollectionsListState.add(collection)
                }.onFailure {
                    //todo handle database failure
                }
        }
    }

    fun deleteCollection(collection: CardCollectionModel){
        viewModelScope.launch(dispatcher) {
            deleteCollectionUseCase.invoke(collection)
                .onSuccess {
                    _cardCollectionsListState.remove(collection)
                }.onFailure {
                    //todo handle database failure
                }
        }
    }

    fun getCardCollectionsFromDatabase(){
        viewModelScope.launch(dispatcher) {
            getCollectionsUseCase.invoke(Unit)
                .onSuccess { collections ->
                    _cardCollectionsListState.addAll(collections)
                }.onFailure {
                    //todo handle database failure
                }
        }
    }

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