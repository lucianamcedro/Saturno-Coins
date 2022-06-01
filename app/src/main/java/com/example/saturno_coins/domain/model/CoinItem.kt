package com.example.saturno_coins.domain.model

import java.io.Serializable

data class CoinItem(
    val asset_id: String,
    val id_icon: String,
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
) : Serializable