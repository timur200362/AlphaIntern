package com.example.alphaintern.domain.usecase.database.interfaces

import com.example.alphaintern.data.database.CardEntity

interface AddCardToDatabaseUseCase {
    suspend operator fun invoke(cardEntity: CardEntity)
}