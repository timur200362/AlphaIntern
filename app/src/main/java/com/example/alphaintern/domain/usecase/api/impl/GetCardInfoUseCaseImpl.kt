package com.example.alphaintern.domain.usecase.api.impl

import com.example.alphaintern.data.remote.response.CardInfo
import com.example.alphaintern.domain.repository.CardInfoRepository
import com.example.alphaintern.domain.usecase.api.interfaces.GetCardInfoUseCase

class GetCardInfoUseCaseImpl(
    private val repository: CardInfoRepository
): GetCardInfoUseCase {
    override suspend fun invoke(binNumber: String): CardInfo {
        return repository.getCardInfo(binNumber)
    }
}