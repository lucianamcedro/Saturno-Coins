package com.example.saturno_coins.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "CoinItem")
data class CoinItem(
    @PrimaryKey()
    val asset_id: String,
    var id_icon: String?,
    val data_end: String,
    val data_orderbook_end: String,
    val data_orderbook_start: String,
    val data_quote_end: String,
    val data_quote_start: String,
    val data_start: String,
    val data_symbols_count: Int,
    val data_trade_end: String,
    val data_trade_start: String,
    val name: String,
    val price_usd: Double,
    val type_is_crypto: Int,
    val volume_1day_usd: Double,
    val volume_1hrs_usd: Double,
    val volume_1mth_usd: Double
) : Serializable {

    fun getImageCoin(): String {
        if (id_icon == null) {
            return "https://www.iconpacks.net/icons/2/free-coin-dollar-icon-2686-thumb.png"
        } else {
            id_icon = id_icon?.replace("-".toRegex(), "")
            return "https://s3.eu-central-1.amazonaws.com//bbxt-static-icons/type-id/png_512/$id_icon.png"
        }
    }
}
