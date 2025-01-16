package com.rikezero.dektek.domain.usecase

import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.domain.model.DekTekResult
import com.rikezero.dektek.domain.repository.CardCollectionRepository

class GetCollectionByNameUseCase(
    private val databaseRepository: CardCollectionRepository
): UseCase<String, CardCollectionModel?>() {
    override suspend fun execute(params: String): DekTekResult<CardCollectionModel?> {
        return databaseRepository.getCollectionByName(params)
    }
}