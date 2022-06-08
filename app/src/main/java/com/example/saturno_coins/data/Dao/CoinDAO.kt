package com.example.saturno_coins.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.service.retrofit.domain.model.CoinItem

@Dao
interface CoinDAO {

    @Insert
    fun save(coin: CoinItem)

    @Delete
    fun delete(coinId: CoinItem)

    @Query("SELECT * FROM CoinItem WHERE asset_id = :id")
    fun load(id: String): CoinItem

    @Query("SELECT * FROM CoinItem")
    fun getInvited(): List<CoinItem>
}
