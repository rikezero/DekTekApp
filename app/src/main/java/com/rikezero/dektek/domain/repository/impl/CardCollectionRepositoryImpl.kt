package com.rikezero.dektek.domain.repository.impl

import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.domain.model.DekTekResult
import com.rikezero.dektek.domain.model.mapToNotNull
import com.rikezero.dektek.domain.model.orDatabaseFailure
import com.rikezero.dektek.domain.model.storageThrowableMap
import com.rikezero.dektek.domain.repository.CardCollectionRepository
import com.rikezero.dektek.networking.response.DekTeKResponse
import com.rikezero.dektek.room.adapter.impl.CardCollectionDatabaseAdapter
import com.rikezero.dektek.room.adapter.impl.CardCollectionMappingDatabaseAdapter
import com.rikezero.dektek.room.adapter.impl.CardsDatabaseAdapter
import com.rikezero.dektek.room.entity.CardCollectionEntity
import com.rikezero.dektek.util.result
import com.rikezero.dektek.util.resultSuspend
import com.rikezero.dektek.util.toModel
import com.rikezero.dektek.util.toResult

class CardCollectionRepositoryImpl(
    private val collectionAdapter: CardCollectionDatabaseAdapter,
    private val cardsAdapter: CardsDatabaseAdapter,
    private val mappingAdapter: CardCollectionMappingDatabaseAdapter
) : CardCollectionRepository {
    override suspend fun addCardCollection(collection: CardCollectionEntity): DekTekResult<Unit> {
        return result {
            collectionAdapter.insert(collection)
        }.storageThrowableMap()
    }

    override suspend fun fetchAllCollections(): DekTekResult<List<CardCollectionModel>> {
        return resultSuspend {
            val collectionsResult = collectionAdapter.getAll()
                .toResult()
                .mapToNotNull {
                    it.map { collection -> collection.toModel() }
                }.orDatabaseFailure("Collection not found")
                .getOrNull()

            collectionsResult?.forEach { collection ->
                val mappingResponse =
                    mappingAdapter.getCardsForCollection(collection.collectionName)
                        .toResult()
                        .orDatabaseFailure("No cards found in the collection")
                        .getOrNull()
                collection.cards.putAll(mappingResponse?.mapKeys { it.key.toModel() }.orEmpty())
            }

            DekTeKResponse.Success(collectionsResult.orEmpty())
        }.storageThrowableMap()
    }

    override suspend fun getCollectionByName(name: String): DekTekResult<CardCollectionModel> {
        return resultSuspend {
            val mappingResponse = mappingAdapter.getCardsForCollection(name)
                .toResult()
                .orDatabaseFailure("No cards found in the collection")
                .getOrNull()
            collectionAdapter.getById(name)
        }.mapToNotNull {
            it.toModel()
        }.storageThrowableMap()
    }

    override suspend fun deleteCollection(collection: CardCollectionEntity): DekTekResult<Unit> {
        return DekTekResult.failure(NotImplementedError())
    }
}
