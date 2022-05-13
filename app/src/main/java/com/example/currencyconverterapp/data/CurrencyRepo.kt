package com.example.currencyconverterapp.data

import android.util.Log
import com.example.currencyconverterapp.api.ConvertApi
import com.example.currencyconverterapp.data.models.Rate
import com.example.currencyconverterapp.uitl.Resource
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrencyRepo @Inject constructor(private val convertApi: ConvertApi) {
//    suspend fun convert(from: String, to: String, amount: String): Response<Rate>? {
//        return convertApi.getRate(from,to,amount)
//    }

    suspend fun getRates(from: String, to: String, amount: String): Resource<Rate> {
        return try {
            val response = convertApi.getRate(from,to,amount)

            val result = response?.body()

            if(response!=null && response.isSuccessful && result!=null){
                Resource.Success(result)
            }else{
                Resource.Error(response?.message()?: "reponse null")
            }

        } catch (e: Exception){
            Resource.Error(e.message?: " an error occured")
        } catch (e: IOException){
            Resource.Error(e.message?: " an error occured")
        } catch (e: HttpException){
            Resource.Error(e.message?: " an error occured")
        }
    }
}