package com.service.retrofit.data.repository

import com.service.retrofit.data.service.IClientService
import com.service.retrofit.domain.model.CoinItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class CoinRepository(
    private val coinClient: IClientService
) : ICoinRepository {
    override suspend fun getCoins(): Call<List<CoinItem>> {
        return withContext(Dispatchers.IO) {
            coinClient.getBreeds()
        }
    }

    override suspend fun getDetailsCoin(coinId: String): Call<List<CoinItem>> {
        return withContext(Dispatchers.IO) {
            coinClient.getDetailsCoins(coinId)
        }
    }
}
