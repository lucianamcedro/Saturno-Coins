package com.example.saturno_coins.data.service

import com.example.saturno_coins.domain.Constants.Companion.API_KEY
import com.example.saturno_coins.domain.Constants.Companion.PATH_URL
import com.example.saturno_coins.domain.model.CoinItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IClientService {
    @GET("$PATH_URL$API_KEY")
    fun getBreeds(): Call<List<CoinItem>>
    @GET("$PATH_URL{assets_id}$API_KEY")
    fun getDetailsCoins(@Path("assets_id") coinId: String): Call<List<CoinItem>>
    fun getList(@Query("$API_KEY") api: String): Call<List<CoinItem>>
    abstract fun getList(): Call<List<CoinItem>>
}
