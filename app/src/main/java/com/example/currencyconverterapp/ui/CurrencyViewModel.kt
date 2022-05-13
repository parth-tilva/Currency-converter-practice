package com.example.currencyconverterapp.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.currencyconverterapp.data.CurrencyRepo
import com.example.currencyconverterapp.data.models.Rate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val repository: CurrencyRepo): ViewModel() {


    private var _liveRate = MutableLiveData<Rate>()
    var liveRate: LiveData<Rate> = _liveRate

    fun convertCurrency(from: String, to: String, amount: String){
        viewModelScope.launch {
            val response = repository.convert(from,to,amount)

            if (response != null) {
                Log.d("Main"," issuccess: ${response.isSuccessful} ")
            }

            if(response!=null && response.isSuccessful && response.body()!=null){
                Log.d("Main","body: ${response.body()} ")
//                _liveRate = liveData {
//                    emit(response.body()!!)
//                } as MutableLiveData<Rate>
                _liveRate.value = response.body()
            }
        }
    }

}