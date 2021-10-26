package com.example.newmed.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmed.server.ApiExchange
import com.example.newmed.server.Exchange
import com.example.newmed.server.ExchangeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceViewModel : ViewModel() {

    val exchangeLiveData = MutableLiveData<List<ExchangeItem>>()


   private val apiExchange = ApiExchange.createRetrofit()

    fun getExchange() {
        viewModelScope.launch(Dispatchers.IO) {
            apiExchange.getExchange().enqueue(object : Callback<List<ExchangeItem>> {
                override fun onResponse(call: Call<List<ExchangeItem>>, response: Response<List<ExchangeItem>>) {
                    exchangeLiveData.value = response.body()
                }

                override fun onFailure(call: Call<List<ExchangeItem>>, t: Throwable) { }

            })

        }

    }
}
