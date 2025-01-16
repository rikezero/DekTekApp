package com.rikezero.dektek.room.adapter.impl

import com.rikezero.dektek.networking.response.DekTeKResponse
import com.rikezero.dektek.room.adapter.DatabaseAdapter
import com.rikezero.dektek.room.dao.CardDao
import com.rikezero.dektek.room.entity.CardEntity
import com.rikezero.dektek.util.toDekTeKResponse

class CardsDatabaseAdapter(
    private val cardDao: CardDao
): DatabaseAdapter<CardEntity> {
    override suspend fun insert(entity: CardEntity): DekTeKResponse<Unit> {
        return runCatching {
            cardDao.insertOrUpdateCard(entity)
        }.toDekTeKResponse()
    }

    override suspend fun update(entity: CardEntity): DekTeKResponse<Unit> {
        return runCatching {
            cardDao.insertOrUpdateCard(entity)
        }.toDekTeKResponse()
    }

    override suspend fun delete(entity: CardEntity): DekTeKResponse<Unit> {
        return runCatching {
            cardDao.deleteCardById(entity.id)
        }.toDekTeKResponse()
    }

    override suspend fun getById(id: String): DekTeKResponse<CardEntity?> {
        return runCatching {
            cardDao.getCardById(id)
        }.toDekTeKResponse()
    }

    override suspend fun getAll(): DekTeKResponse<List<CardEntity>> {
        return runCatching {
            cardDao.getAllCards()
        }.toDekTeKResponse()
    }
}