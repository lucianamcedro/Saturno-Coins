package com.example.saturno_coins.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object {
        const val BASE_URL = "https://rest.coinapi.io/"
        const val PATH_URL = "v1/assets/"
        const val API_KEY = "?apikey=6B3B8A37-1BD4-408F-BB2B-7AEDA901D830"
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val date = Calendar.getInstance().time
        val format = SimpleDateFormat("dd MM yyyy")
        return format.format(date)
    }
}
