package com.example.currencyconverterapp.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
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
            val from = binding.spinnerFrom.selectedItem.toString()
            val to = binding.spinnerTo.selectedItem.toString()
            viewModel.convert(from,to,amount)
        }

        lifecycleScope.launchWhenStarted {
             viewModel.conversion.collect{ event->
                 when(event){
                     is  CurrencyViewModel.CurrencyEvent.Success  -> {
                         binding.progressBar.isVisible = false
                         binding.tvAns.setTextColor(Color.BLACK)
                         binding.tvAns.text = event.resultText

                     }
                     is CurrencyViewModel.CurrencyEvent.Failure -> {
                         binding.progressBar.isVisible = false
                         binding.tvAns.setTextColor(Color.RED)
                         binding.tvAns.text = event.errorText
                     }
                     is CurrencyViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                         binding.tvAns.text = ""
                     }
                     else -> Unit

                 }
             }
        }
    }
}