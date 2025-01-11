package com.example.alphaintern.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity (
    @PrimaryKey(autoGenerate = true) val cardId: Int = 0,
    @ColumnInfo(name = "binCard") val bin: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "typeCard") val type: String,
    @ColumnInfo(name = "bankUrl") val bankUrl: String,
    @ColumnInfo(name = "bankPhone") val bankPhone: String,
    @ColumnInfo(name = "bankName") val bankName: String,
    @ColumnInfo(name = "bankCity") val bankCity: String,
)