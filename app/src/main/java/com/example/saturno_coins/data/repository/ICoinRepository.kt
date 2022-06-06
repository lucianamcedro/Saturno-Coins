package com.example.saturno_coins.data.repository

import com.example.saturno_coins.domain.model.CoinItem
import retrofit2.Call

interface ICoinRepository {
    suspend fun getCoins(): Call<List<CoinItem>>
    suspend fun getDetailsCoin(coinId: String): Call<List<CoinItem>>
}
