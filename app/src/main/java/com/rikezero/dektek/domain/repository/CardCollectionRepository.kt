package com.rikezero.dektek.domain.repository

import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.domain.model.DekTekResult
import com.rikezero.dektek.room.entity.CardCollectionEntity

interface CardCollectionRepository {
    suspend fun addCardCollection(collection: CardCollectionEntity): DekTekResult<Unit>
    suspend fun fetchAllCollections(): DekTekResult<List<CardCollectionModel>>
    suspend fun getCollectionByName(name: String): DekTekResult<CardCollectionModel>
    suspend fun deleteCollection(collection: CardCollectionEntity): DekTekResult<Unit>
}