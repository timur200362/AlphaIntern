package com.example.alphaintern.domain.usecase.database.impl

import com.example.alphaintern.data.database.CardEntity
import com.example.alphaintern.domain.repository.database.CardDatabaseRepository
import com.example.alphaintern.domain.usecase.database.interfaces.GetSearchHistoryCardsFromDatabaseUseCase

class GetSearchHistoryCardsFromDatabaseUseCaseImpl(
    private val repository: CardDatabaseRepository
): GetSearchHistoryCardsFromDatabaseUseCase {
    override suspend fun invoke(): List<CardEntity> {
        return repository.getAllFromDatabase()
    }
}