package com.example.alphaintern.domain.usecase.database.impl

import com.example.alphaintern.data.database.CardEntity
import com.example.alphaintern.domain.repository.database.CardDatabaseRepository
import com.example.alphaintern.domain.usecase.database.interfaces.AddCardToDatabaseUseCase

class AddCardToDatabaseUseCaseImpl(
    private val repository: CardDatabaseRepository
): AddCardToDatabaseUseCase {
    override suspend fun invoke(cardEntity: CardEntity) {
        repository.addToDatabase(cardEntity)
    }
}