package com.example.alphaintern.di

import com.example.alphaintern.domain.usecase.api.impl.GetCardInfoUseCaseImpl
import com.example.alphaintern.domain.usecase.api.interfaces.GetCardInfoUseCase
import com.example.alphaintern.domain.usecase.database.impl.AddCardToDatabaseUseCaseImpl
import com.example.alphaintern.domain.usecase.database.impl.GetSearchHistoryCardsFromDatabaseUseCaseImpl
import com.example.alphaintern.domain.usecase.database.interfaces.AddCardToDatabaseUseCase
import com.example.alphaintern.domain.usecase.database.interfaces.GetSearchHistoryCardsFromDatabaseUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetCardInfoUseCase> { GetCardInfoUseCaseImpl(get()) }
    factory<AddCardToDatabaseUseCase> { AddCardToDatabaseUseCaseImpl(get()) }
    factory<GetSearchHistoryCardsFromDatabaseUseCase> { GetSearchHistoryCardsFromDatabaseUseCaseImpl(get()) }
}