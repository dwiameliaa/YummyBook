package com.example.yummybook

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class MealDetailResponse(
    @field:SerializedName("meals")
    val meals: List<MealDetailItem>
) : Parcelable

@Parcelize
data class MealDetailItem(

    @field:SerializedName("idMeal")
    val idMeal: String,

    @field:SerializedName("strMeal")
    val strMeal: String,

    @field:SerializedName("strCategory")
    val strCategory: String?,

    @field:SerializedName("strInstructions")
    val strInstructions: String?,

    @field:SerializedName("strMealThumb")
    val strMealThumb: String?,

    @field:SerializedName("strIngredient1")
    val strIngredient1: String,

    @field:SerializedName("strIngredient2")
    val strIngredient2: String?,

    @field:SerializedName("strIngredient3")
    val strIngredient3: String?,

    @field:SerializedName("strIngredient4")
    val strIngredient4: String?,

    @field:SerializedName("strIngredient5")
    val strIngredient5: String?,

    @field:SerializedName("strIngredient6")
    val strIngredient6: String?,

    @field:SerializedName("strIngredient7")
    val strIngredient7: String?,

    @field:SerializedName("strIngredient8")
    val strIngredient8: String?,

    @field:SerializedName("strIngredient9")
    val strIngredient9: String?,

    @field:SerializedName("strIngredient10")
    val strIngredient10: String?,

    @field:SerializedName("strMeasure1")
    val strMeasure1: String?,

    @field:SerializedName("strMeasure2")
    val strMeasure2: String?,

    @field:SerializedName("strMeasure3")
    val strMeasure3: String?,

    @field:SerializedName("strMeasure4")
    val strMeasure4: String?,

    @field:SerializedName("strMeasure5")
    val strMeasure5: String?,

    @field:SerializedName("strMeasure6")
    val strMeasure6: String?,

    @field:SerializedName("strMeasure7")
    val strMeasure7: String?,

    @field:SerializedName("strMeasure8")
    val strMeasure8: String?,

    @field:SerializedName("strMeasure9")
    val strMeasure9: String?,

    @field:SerializedName("strMeasure10")
    val strMeasure10: String?
) : Parcelable
