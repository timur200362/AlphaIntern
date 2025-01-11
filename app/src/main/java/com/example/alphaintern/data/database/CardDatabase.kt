package com.example.alphaintern.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CardEntity::class],
    version = 1,
    autoMigrations = [

    ])
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}