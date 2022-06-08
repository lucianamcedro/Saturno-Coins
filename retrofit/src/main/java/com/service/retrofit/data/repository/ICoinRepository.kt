package com.service.retrofit.data.repository

import com.service.retrofit.domain.model.CoinItem
import retrofit2.Call

interface ICoinRepository {
    suspend fun getCoins(): Call<List<CoinItem>>
    suspend fun getDetailsCoin(coinId: String): Call<List<CoinItem>>
}
