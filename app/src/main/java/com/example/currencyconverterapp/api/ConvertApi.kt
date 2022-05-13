package com.example.currencyconverterapp.api
import com.example.currencyconverterapp.BuildConfig
import com.example.currencyconverterapp.data.models.Rate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ConvertApi {

    companion object{
        const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"
        const val CLIENT_ID = BuildConfig.EXCHANGE_API
    }

    @Headers("apikey: $CLIENT_ID")
    @GET("convert")
    suspend fun getRate(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String
    ): Response<Rate>?  // ? or not ??
}