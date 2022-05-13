package com.example.currencyconverterapp.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.currencyconverterapp.data.CurrencyRepo
import com.example.currencyconverterapp.data.models.Rate
import com.example.currencyconverterapp.uitl.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val repository: CurrencyRepo): ViewModel() {


    sealed class CurrencyEvent{
        class Success(val resultText: String): CurrencyEvent( )
        class Failure(val errorText: String): CurrencyEvent()
        object Empty: CurrencyEvent()
        object Loading: CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion

    fun convert(
        from: String,
        to: String,
        amount: String
    ){
        if(amount==""){
            _conversion.value = CurrencyEvent.Failure("Please Enter amount")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = CurrencyEvent.Loading
            when(val rateResponse = repository.getRates(from,to,amount)){
                is Resource.Error -> _conversion.value = CurrencyEvent.Failure(rateResponse.message!!)
                is Resource.Success -> {
                    val rate = rateResponse.data!!.result
                    val roundR = String.format("%.2f",rate)
                    val ans = "$amount $from = $roundR $to"
                    _conversion.value = CurrencyEvent.Success(ans)
                }
            }
        }
    }

}