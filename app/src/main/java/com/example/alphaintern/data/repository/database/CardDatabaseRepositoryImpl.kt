package com.example.alphaintern.data.repository.database

import com.example.alphaintern.data.database.CardDatabase
import com.example.alphaintern.data.database.CardEntity
import com.example.alphaintern.domain.repository.database.CardDatabaseRepository

class CardDatabaseRepositoryImpl(
    private val cardDatabase: CardDatabase
): CardDatabaseRepository {
    private val cardDao = cardDatabase.cardDao()

    override suspend fun addToDatabase(cardEntity: CardEntity) {
        cardDao.insert(cardEntity)
    }

    override suspend fun getAllFromDatabase(): List<CardEntity> {
        return cardDao.getAll()
    }
}