package com.example.saturno_coins.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object {
        const val BASE_URL = "https://rest.coinapi.io/"
        const val PATH_URL = "v1/assets/"
        const val API_KEY = "?apikey=7521D960-087E-4FD3-8493-25453D063919"
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val date = Calendar.getInstance().time
        val format = SimpleDateFormat("dd MM yyyy")
        return format.format(date)
    }
}
