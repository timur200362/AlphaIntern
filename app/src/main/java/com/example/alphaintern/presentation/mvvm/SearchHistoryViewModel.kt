package com.example.alphaintern.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alphaintern.data.database.CardEntity
import com.example.alphaintern.domain.usecase.database.interfaces.GetSearchHistoryCardsFromDatabaseUseCase
import kotlinx.coroutines.launch

class SearchHistoryViewModel(
    private val getSearchHistoryCardsFromDatabaseUseCase: GetSearchHistoryCardsFromDatabaseUseCase
) : ViewModel() {

    private val _cards = MutableLiveData<List<CardEntity>>(emptyList())
    val cards: LiveData<List<CardEntity>> get() = _cards

    init {
        loadSearchHistoryCards()
    }

    private fun loadSearchHistoryCards() {
        viewModelScope.launch {
            val history = getSearchHistoryCardsFromDatabaseUseCase()
            _cards.value = history
        }
    }
}
