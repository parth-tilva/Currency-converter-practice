package com.example.currencyconverterapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.currencyconverterapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel : CurrencyViewModel by viewModels()
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

            viewModel.convertCurrency(from,to,amount)

//
//            val api = retrofit.create(ConvertApi::class.java)
//
//            lifecycleScope.launch {
//                val response = try {
//                        api.getRate(from,to,amount)
//                }catch (e: IOException){
//                    Log.d("Main","io $e")
//                    return@launch
//                }catch (e: HttpException){
//                    Log.d("Main","http $e")
//                    return@launch
//                }
//
//                if(response.isSuccessful && response.body()!=null){
//                    val b = response.body()!!
//                    Log.d("Main","body: $b ")
//
//                    if(b.success){
//                        val rate = b.result
//                        val rr = String.format("%.2f", rate)
//                        withContext(Dispatchers.Main) {
//                            binding.tvAns.text = "$amount $from = $to $rr "
//                        }
//                    }
//                }else{
//                        Log.d("Main","some error")
//                }
//            }
        }

        viewModel.liveRate.observe(this, Observer {
                                    Log.d("Main","observer claled")

            val b = it
                    Log.d("Main","body: $b ")
                    if(b.success){
                        val rate = b.result
                        val rr = String.format("%.2f", rate)
                            binding.tvAns.text = "amount from = to $rr "
                    }
        })



    }
}