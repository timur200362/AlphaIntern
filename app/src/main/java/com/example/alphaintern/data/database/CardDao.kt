package com.example.alphaintern.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CardDao {
    @Insert
    suspend fun insert(cardEntity: CardEntity)

    @Query("SELECT * FROM cards")
    suspend fun getAll(): List<CardEntity>

    @Transaction
    suspend fun insertAndGetAll(cardEntity: CardEntity): List<CardEntity> {
        insert(cardEntity)
        return getAll()
    }
}