package com.example.currencyconverterapp.data

import com.example.currencyconverterapp.api.ConvertApi
import com.example.currencyconverterapp.data.models.Rate
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrencyRepo @Inject constructor(private val convertApi: ConvertApi) {
    suspend fun convert(from: String, to: String, amount: String): Response<Rate>? {
        return convertApi.getRate(from,to,amount)
    }
}