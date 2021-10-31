package com.example.newmed.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ApiExchange {
    @GET("kursExchange/")
    fun getExchange(): Call<List<ExchangeItem>>

    companion object {
        val BASE_URL = "https://belarusbank.by/api/"

        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        fun createRetrofit(): ApiExchange {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create()
        }
    }
}