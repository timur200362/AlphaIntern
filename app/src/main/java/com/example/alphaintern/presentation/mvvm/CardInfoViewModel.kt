package com.example.alphaintern.presentation.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alphaintern.data.database.CardEntity
import com.example.alphaintern.data.remote.response.CardInfo
import com.example.alphaintern.domain.usecase.api.interfaces.GetCardInfoUseCase
import com.example.alphaintern.domain.usecase.database.interfaces.AddCardToDatabaseUseCase
import com.example.alphaintern.presentation.mapper.CardEntityMapper
import kotlinx.coroutines.launch

class CardInfoViewModel(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val addCardToDatabaseUseCase: AddCardToDatabaseUseCase,
    private val cardEntityMapper: CardEntityMapper
): ViewModel() {
    private val _cardInfo = MutableLiveData<CardInfo?>()
    val cardInfo: LiveData<CardInfo?> get() = _cardInfo

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun getCardInfo(binNumber: String) {
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null
            try {
                val info = getCardInfoUseCase(binNumber)
                _cardInfo.value = info

                info.let {
                    val cardEntity = cardEntityMapper.mapToCardEntity(binNumber, info)
                    addCardToDatabaseUseCase(cardEntity)
                }

            } catch (e: Exception) {
                Log.e("CardInfoViewModel", "Ошибка при загрузке данных: ${e.message}", e)
                _errorMessage.value = "Ошибка при загрузке данных"
                _cardInfo.value = null
            } finally {
                _loading.value = false
            }
        }
    }
}