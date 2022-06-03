package com.example.saturno_coins.data.Dao

import android.content.Context
import com.example.saturno_coins.domain.model.CoinItem

class CoinDaoRepository {
    private lateinit var context: Context
    private val mDataBase = CoinDatabase.getDatabase(context).coinDAO()

    private fun AdicionarFavorite(coin: CoinItem) {
        return mDataBase.save(coin)
    }
    private fun deleteFavorite(coin: CoinItem) {
        mDataBase.delete(coin)
    }
    private fun listFavorite(): List<CoinItem> {
        return mDataBase.getInvited()
    }

    private fun loadFavorite(id: String): CoinItem {
        return mDataBase.load(id)
    }
}
