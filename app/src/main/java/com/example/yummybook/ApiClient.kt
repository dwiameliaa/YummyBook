package com.example.yummybook.network

import com.example.yummybook.MealApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val instance: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }
}
