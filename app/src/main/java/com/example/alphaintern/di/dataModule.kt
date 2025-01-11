package com.example.alphaintern.di

import androidx.room.Room
import com.example.alphaintern.data.database.CardDatabase
import com.example.alphaintern.data.remote.ApiFactory
import com.example.alphaintern.data.repository.api.CardInfoRepositoryImpl
import com.example.alphaintern.data.repository.database.CardDatabaseRepositoryImpl
import com.example.alphaintern.domain.repository.api.CardInfoRepository
import com.example.alphaintern.domain.repository.database.CardDatabaseRepository
import org.koin.dsl.module

val dataModule = module {
    single { ApiFactory().binApi }
    single<CardInfoRepository> { CardInfoRepositoryImpl(get()) }

    single { Room.databaseBuilder(get(), CardDatabase::class.java, "").build() }
    single { get<CardDatabase>().cardDao() }
    single<CardDatabaseRepository> { CardDatabaseRepositoryImpl(get()) }
}