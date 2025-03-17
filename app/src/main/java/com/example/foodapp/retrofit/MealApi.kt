package com.example.foodapp.retrofit

import com.example.foodapp.models.CategoryList
import com.example.foodapp.models.CategoryRealLIst
import com.example.foodapp.models.MealsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET ("random.php")
    fun getRandomMeal(): Call<MealsList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String): Call<MealsList>

    @GET("filter.php?")
    fun getPopularItems (@Query("c") categoryName : String): Call<CategoryList>

    @GET("categories.php")
    fun getCategoryItems (): Call<CategoryRealLIst>

    @GET("filter.php")
    fun getMealsByCategory (@Query("c") categoryName : String): Call<CategoryList>
}