package com.example.saturno_coins.data.service

import com.example.saturno_coins.domain.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientService {
    companion object {
        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        val coinClientService: IClientService by lazy {
            getRetrofitInstance().create(IClientService::class.java)
        }
    }
}
