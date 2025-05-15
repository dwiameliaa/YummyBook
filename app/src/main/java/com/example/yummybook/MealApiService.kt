package com.example.yummybook
import com.example.yummybook.MealResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("filter.php")
    fun getMealsByCategory(
        @Query("c") category: String
    ): Call<MealResponse>

    @GET("lookup.php")
    fun getMealDetail(
        @Query("i") id: String
    ): Call<MealDetailResponse>

// mengirim permintaan ke server API
}
