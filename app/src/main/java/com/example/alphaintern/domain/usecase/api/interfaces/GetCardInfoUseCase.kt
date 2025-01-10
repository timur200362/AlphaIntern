package com.example.alphaintern.domain.usecase.api.interfaces

import com.example.alphaintern.data.remote.response.CardInfo

interface GetCardInfoUseCase {
    suspend operator fun invoke(binNumber: String): CardInfo
}