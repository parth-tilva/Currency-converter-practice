package com.example.currencyconverterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.versionedparcelable.ParcelField
import com.example.currencyconverterapp.api.ConvertApi
import com.example.currencyconverterapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import retrofit2.Retrofit
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            val amount = binding.etAmount.text.toString()
            val from = "USD"//binding.spinnerFrom.
            val to = "INR"

            val api = retrofit.create(ConvertApi::class.java)

            lifecycleScope.launch {
                    val response = try {
                        val r = api.getRate(from,to,amount)

                }catch (e: IOException){
                    Log.d("Main","io $e")
                    return@launch
                }catch (e: HttpException){
                    Log.d("Main","http $e")
                    return@launch
                }
            }
        }

    }
}