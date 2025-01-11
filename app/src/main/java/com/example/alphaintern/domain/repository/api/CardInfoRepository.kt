package com.example.alphaintern.domain.repository.api

import com.example.alphaintern.data.remote.response.CardInfo

interface CardInfoRepository {
    suspend fun getCardInfo(binNumber: String): CardInfo
}