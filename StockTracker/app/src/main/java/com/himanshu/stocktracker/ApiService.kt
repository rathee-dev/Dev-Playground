package com.himanshu.stocktracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://api.coingecko.com/api/v3/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val cryptoService = retrofit.create(CryptoApi::class.java)
interface CryptoApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "usd"
    ): List<Crypto>
}