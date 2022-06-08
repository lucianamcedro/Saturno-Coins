package com.service.retrofit.data.service

import com.service.retrofit.domain.Constants.Companion.API_KEY
import com.service.retrofit.domain.Constants.Companion.PATH_URL
import com.service.retrofit.domain.model.CoinItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IClientService {
    @GET("$PATH_URL$API_KEY")
    fun getBreeds(): Call<List<CoinItem>>
    @GET("$PATH_URL{assets_id}$API_KEY")
    fun getDetailsCoins(@Path("assets_id") coinId: String): Call<List<CoinItem>>
}
