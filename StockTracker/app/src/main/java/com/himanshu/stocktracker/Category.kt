package com.himanshu.stocktracker

import com.google.gson.annotations.SerializedName

data class Crypto(
//    val id: String,
//    val symbol: String,
    val name: String,
    val image: String,

    @SerializedName("current_price")
    val currentPrice: Double,

//    @SerializedName("market_cap")
//    val marketCap: Long,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double
)
