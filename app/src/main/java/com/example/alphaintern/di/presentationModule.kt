package com.example.alphaintern.di

import com.example.alphaintern.presentation.mapper.CardEntityMapper
import com.example.alphaintern.presentation.mvvm.CardInfoViewModel
import com.example.alphaintern.presentation.mvvm.SearchHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single { CardEntityMapper() }
    viewModel<CardInfoViewModel> { CardInfoViewModel(get(), get(), get()) }
    viewModel<SearchHistoryViewModel> { SearchHistoryViewModel(get()) }
}