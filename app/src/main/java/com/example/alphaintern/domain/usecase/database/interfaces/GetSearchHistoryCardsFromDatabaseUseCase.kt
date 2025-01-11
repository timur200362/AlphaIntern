package com.example.alphaintern.domain.usecase.database.interfaces

import com.example.alphaintern.data.database.CardEntity

interface GetSearchHistoryCardsFromDatabaseUseCase {
    suspend operator fun invoke(): List<CardEntity>
}