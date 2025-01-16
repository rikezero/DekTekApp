package com.rikezero.dektek.domain.usecase

import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.domain.model.DekTekResult
import com.rikezero.dektek.domain.repository.CardCollectionRepository

class GetCollectionsUseCase(
    private val databaseRepository: CardCollectionRepository
) : UseCase<Unit, List<CardCollectionModel>>() {
    override suspend fun execute(params: Unit): DekTekResult<List<CardCollectionModel>> {
        return databaseRepository.fetchAllCollections()
    }
}