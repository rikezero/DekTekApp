package com.rikezero.dektek.room.adapter.impl

import com.rikezero.dektek.networking.response.DekTeKResponse
import com.rikezero.dektek.room.adapter.DatabaseAdapter
import com.rikezero.dektek.room.dao.CardCollectionMappingDao
import com.rikezero.dektek.room.entity.CardCollectionMappingEntity
import com.rikezero.dektek.room.entity.CardEntity
import com.rikezero.dektek.util.toDekTeKResponse

class CardCollectionMappingDatabaseAdapter(
    private val mappingDao: CardCollectionMappingDao
): DatabaseAdapter<CardCollectionMappingEntity> {
    override suspend fun insert(entity: CardCollectionMappingEntity): DekTeKResponse<Unit> {
        return runCatching {
            mappingDao.insertOrUpdateMapping(entity)
        }.toDekTeKResponse()
    }

    override suspend fun update(entity: CardCollectionMappingEntity): DekTeKResponse<Unit> {
        return runCatching {
            mappingDao.insertOrUpdateMapping(entity)
        }.toDekTeKResponse()
    }

    override suspend fun delete(entity: CardCollectionMappingEntity): DekTeKResponse<Unit> {
        return runCatching {
            mappingDao.deleteMapping(entity.collectionName, entity.cardId)
        }.toDekTeKResponse()
    }

    suspend fun getCardsForCollection(collectionName: String): DekTeKResponse<Map<CardEntity,Int>?> {
        return runCatching {
            mappingDao.getCardsWithQuantities(collectionName).associate { entries -> entries.card to entries.quantity }
        }.toDekTeKResponse()
    }
}