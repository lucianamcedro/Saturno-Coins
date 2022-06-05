package com.example.saturno_coins.data.Dao

import android.content.Context
import androidx.room.Room
import com.example.saturno_coins.domain.model.CoinItem

class CoinDaoRepository {
    companion object {
        private lateinit var context: Context
        fun setContext(contextCoin: Context) {
            context = contextCoin
        }
    }

    fun loadDatabase(id: String): CoinItem {
        val databaseCoin = Room
            .databaseBuilder(context, CoinDatabase::class.java, "coin")
            .allowMainThreadQueries()
            .build()
        return databaseCoin.coinDao().load(id)
    }

    fun addFavorite(coin: CoinItem) {
        val databaseCoin = Room
            .databaseBuilder(context, CoinDatabase::class.java, "coin")
            .allowMainThreadQueries()
            .build()
        databaseCoin.coinDao().save(coin)
    }

    fun deleteFavorite(coinId: CoinItem) {
        val databaseCoin = Room
            .databaseBuilder(context, CoinDatabase::class.java, "coin")
            .allowMainThreadQueries()
            .build()
        return databaseCoin.coinDao().delete(coinId)
    }

    fun listFavorite(): List<CoinItem> {
        val databaseCoin = Room
            .databaseBuilder(context, CoinDatabase::class.java, "coin")
            .allowMainThreadQueries()
            .build()
        return databaseCoin.coinDao().getInvited()
    }
}
