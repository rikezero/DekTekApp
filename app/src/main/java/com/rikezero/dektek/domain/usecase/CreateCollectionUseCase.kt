package com.rikezero.dektek.domain.usecase

import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.domain.model.DekTekResult
import com.rikezero.dektek.domain.repository.CardCollectionRepository
import com.rikezero.dektek.util.toEntity

class CreateCollectionUseCase(
    private val databaseRepository: CardCollectionRepository
) : UseCase<CardCollectionModel, Unit>() {
    override suspend fun execute(params: CardCollectionModel): DekTekResult<Unit> {
        return databaseRepository.addCardCollection(params.toEntity())
    }
}