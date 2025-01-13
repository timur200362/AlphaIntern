package com.example.alphaintern.presentation.mapper

import com.example.alphaintern.data.database.CardEntity
import com.example.alphaintern.data.remote.response.CardInfo

class CardEntityMapper {
    fun mapToCardEntity(binNumber: String, info: CardInfo): CardEntity {
        return CardEntity(
            bin = binNumber,
            country = info.country.name,
            latitude = info.country.latitude,
            longitude = info.country.longitude,
            type = info.type,
            bankUrl = info.bank.url,
            bankPhone = info.bank.phone,
            bankWebsite = info.bank.name,
            bankCity = info.bank.city
        )
    }
}