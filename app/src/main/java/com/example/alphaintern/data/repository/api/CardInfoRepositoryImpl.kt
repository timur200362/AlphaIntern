package com.example.alphaintern.data.repository.api

import com.example.alphaintern.data.remote.ApiService
import com.example.alphaintern.data.remote.response.CardInfo
import com.example.alphaintern.domain.repository.api.CardInfoRepository

class CardInfoRepositoryImpl(
    private val apiService: ApiService
): CardInfoRepository {
    override suspend fun getCardInfo(binNumber: String): CardInfo {
        return apiService.loadCardInformation(binNumber)
    }
}