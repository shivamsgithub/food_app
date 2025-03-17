package com.example.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.models.CategoryList
import com.example.foodapp.models.CategoryMeals
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel : ViewModel() {

    private var categoryMealLiveData: MutableLiveData<CategoryMeals> = MutableLiveData()

    fun getMealsByCategory(categoryName : String) {
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<CategoryList> {

            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null) {
                    categoryMealLiveData.value = response.body()!!.meals[0]

                } else {
                    Log.d("CategoryMealsViewModel", "Response body is null")
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("CategoryMealsViewModel", "Error: ${t.message}")
            }
        })
    }

    fun observerCategoryLiveData() : LiveData<CategoryMeals> {
        return categoryMealLiveData
    }
}