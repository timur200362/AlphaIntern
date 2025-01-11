package com.example.alphaintern.presentation.mvvm

import androidx.lifecycle.ViewModel
import com.example.alphaintern.domain.usecase.database.interfaces.AddCardToDatabaseUseCase
import com.example.alphaintern.domain.usecase.database.interfaces.GetSearchHistoryCardsFromDatabaseUseCase

class SearchHistoryViewModel(
    private val addCardToDatabaseUseCase: AddCardToDatabaseUseCase,
    private val getSearchHistoryCardsFromDatabaseUseCase: GetSearchHistoryCardsFromDatabaseUseCase
): ViewModel() {

}