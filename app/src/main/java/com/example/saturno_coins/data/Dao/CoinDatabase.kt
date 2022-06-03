package com.example.saturno_coins.data.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.saturno_coins.domain.model.CoinItem

@Database(entities = [CoinItem::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDAO(): CoinDAO

    companion object {
        private lateinit var INSTANCE: CoinDatabase

        fun getDatabase(context: Context): CoinDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(CoinDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, CoinDatabase::class.java, "coinDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
