package com.example.alphaintern.di

import com.example.alphaintern.data.remote.ApiFactory
import com.example.alphaintern.data.repository.CardInfoRepositoryImpl
import com.example.alphaintern.domain.repository.CardInfoRepository
import com.example.alphaintern.domain.usecase.api.impl.GetCardInfoUseCaseImpl
import com.example.alphaintern.domain.usecase.api.interfaces.GetCardInfoUseCase
import com.example.alphaintern.presentation.mvvm.CardInfoViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single { ApiFactory().binApi }
    single<CardInfoRepository> { CardInfoRepositoryImpl(get()) }
    factory<GetCardInfoUseCase> { GetCardInfoUseCaseImpl(get()) }
    viewModel<CardInfoViewModel> { CardInfoViewModel(get()) }
}