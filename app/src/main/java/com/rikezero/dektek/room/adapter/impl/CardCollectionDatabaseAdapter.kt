package com.rikezero.dektek.room.adapter.impl

import com.rikezero.dektek.networking.response.DekTeKResponse
import com.rikezero.dektek.room.adapter.DatabaseAdapter
import com.rikezero.dektek.room.dao.CardCollectionDao
import com.rikezero.dektek.room.entity.CardCollectionEntity
import com.rikezero.dektek.util.toDekTeKResponse


class CardCollectionDatabaseAdapter(
    private val collectionDao: CardCollectionDao
) : DatabaseAdapter<CardCollectionEntity> {

    override suspend fun insert(entity: CardCollectionEntity): DekTeKResponse<Unit> {
        return runCatching {
            collectionDao.insertCollection(entity)
        }.toDekTeKResponse()
    }

    override suspend fun update(entity: CardCollectionEntity): DekTeKResponse<Unit> {
        return runCatching {
            collectionDao.insertCollection(entity)
        }.toDekTeKResponse()
    }

    override suspend fun delete(entity: CardCollectionEntity): DekTeKResponse<Unit> {
        return runCatching {
            collectionDao.deleteCollectionByName(entity.collectionName)
        }.toDekTeKResponse()
    }

    override suspend fun getById(id: String): DekTeKResponse<CardCollectionEntity?> {
        return runCatching {
            collectionDao.getCollectionByName(id)
        }.toDekTeKResponse()
    }

    override suspend fun getAll(): DekTeKResponse<List<CardCollectionEntity>> {
        return runCatching {
            collectionDao.getAllCollections()
        }.toDekTeKResponse()
    }
}
