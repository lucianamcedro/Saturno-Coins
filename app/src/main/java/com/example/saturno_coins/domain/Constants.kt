package com.example.saturno_coins.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object {
        const val BASE_URL = "https://rest.coinapi.io/"
        const val PATH_URL = "v1/assets/"
        const val API_KEY = "?apikey=BB17B0AE-AB0D-4FF0-87FD-307417905725"
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val date = Calendar.getInstance().time
        val format = SimpleDateFormat("dd MM yyyy")
        return format.format(date)
    }
}
