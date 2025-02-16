package com.example.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.models.CategoryList
import com.example.foodapp.models.CategoryMeals
import com.example.foodapp.models.Meal
import com.example.foodapp.models.MealsList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
class HomeViewModel(): ViewModel() {

    private var randomMealLiveData : MutableLiveData<Meal>()

    fun getRandomMeal(){

        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealsList> {


            // ctrl + i generated onResponse and onFailure

            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                if (response.body() != null){

                    // !! added for non null
                    val randomMeal : Meal = response.body()!!.meals[0]

                    randomMealLiveData.value = randomMeal
//                    Log.d("TEST", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun observeRandomMealLiveData(): LiveData<Meal>{
        return  randomMealLiveData
    }


}*/

class HomeViewModel : ViewModel() {

    private var randomMealLiveData: MutableLiveData<Meal> = MutableLiveData()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()

    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealsList> {

            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                } else {
                    Log.d("HomeViewModel", "Response body is null")
                }
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.d("HomeViewModel", "Error: ${t.message}")
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback <CategoryList>{

            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observePopularItemsLivedata(): LiveData<List<CategoryMeals>>{
        return  popularItemsLiveData
    }
}