package com.example.alphaintern.data.remote

import com.example.alphaintern.data.remote.response.CardInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{binNumber}")
    suspend fun loadCardInformation(@Path("binNumber") binNumber: String): CardInfo
}