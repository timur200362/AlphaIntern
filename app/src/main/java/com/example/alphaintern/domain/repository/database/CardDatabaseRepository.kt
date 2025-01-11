package com.example.alphaintern.domain.repository.database

import com.example.alphaintern.data.database.CardEntity

interface CardDatabaseRepository {
    suspend fun addToDatabase(cardEntity: CardEntity)
    suspend fun getAllFromDatabase(): List<CardEntity>
}