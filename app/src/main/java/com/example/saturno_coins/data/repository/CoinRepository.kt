package com.example.saturno_coins.data.repository

import android.util.Log
import com.example.saturno_coins.data.service.IClientService
import com.example.saturno_coins.domain.model.CoinItem
import com.example.saturno_coins.presenter.view.CoinErrorActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRepository(
    private val coinClient: IClientService
) : ICoinRepository {
    override suspend fun getCoins(): Call<List<CoinItem>> {
        return withContext(Dispatchers.IO) {
            coinClient.getBreeds().enqueue(object : Callback<List<CoinItem>> {
                override fun onResponse(
                    call: Call<List<CoinItem>>?,
                    response: Response<List<CoinItem>>?
                ) {
                    if (response?.code() == 400) {
                        Log.e("BadRequest", "There is something wrong with your request.")
                        // Toast.makeText(this, "your message", Toast.LENGTH_LONG).show()
                    } else if (response?.code() == 401) {
                        Log.e("Unauthorized", "Your APIKey is wrong")
                    } else if (response?.code() == 403) {
                        Log.e(
                            "Forbidden",
                            "Your API key doesnt't have enough privileges to access this resource "
                        )
                    } else if (response?.code() == 429) {
                        Log.e("Too many requests", "You have exceeded your API key rate limits")
                    } else if (response?.code() == 500) {
                        Log.e(
                            "No data",
                            "You requested specific single item thatwedon't have at this"
                        )
                    } else if (response?.body() != null) {
                        response.body()
                    }
                }

                override fun onFailure(call: Call<List<CoinItem>>?, t: Throwable?) {}
            })

            coinClient.getBreeds()
        }
    }

    override suspend fun getDetailsCoin(coinId: String): Call<List<CoinItem>> {
        return withContext(Dispatchers.IO) {
            coinClient.getDetailsCoins(coinId).enqueue(object : Callback<List<CoinItem>> {
                override fun onResponse(
                    call: Call<List<CoinItem>>?,
                    response: Response<List<CoinItem>>?
                ) {
                    if (response?.code() == 400) {
                        Log.e("BadRequest", "There is something wrong with your request.")
                        // Toast.makeText(this, "your message", Toast.LENGTH_LONG).show()
                    } else if (response?.code() == 401) {
                        Log.e("Unauthorized", "Your APIKey is wrong")
                    } else if (response?.code() == 403) {
                        Log.e(
                            "Forbidden",
                            "Your API key doesnt't have enough privileges to access this resource "
                        )
                    } else if (response?.code() == 429) {
                        Log.e("Too many requests", "You have exceeded your API key rate limits")
                    } else if (response?.code() == 500) {
                        Log.e(
                            "No data",
                            "You requested specific single item thatwedon't have at this"
                        )
                    } else if (response?.body() != null) {
                        response.body()
                    }
                }

                override fun onFailure(call: Call<List<CoinItem>>?, t: Throwable?) {}
            })

            coinClient.getDetailsCoins(coinId)
        }
    }
}