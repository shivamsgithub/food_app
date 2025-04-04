package com.example.foodapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapters.CategoriesAdapter
import com.example.foodapp.databinding.ActivityCategoryDetailBinding
import com.example.foodapp.fragment.HomeFragment
import com.example.foodapp.fragment.HomeFragment.Companion.CATEGORY_NAME
import com.example.foodapp.models.CategoryMeals
import com.example.foodapp.viewModel.CategoryMealsViewModel

class CategoryDetailActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var mealMvvm: CategoryMealsViewModel
    private lateinit var categoriesAdapter : CategoriesAdapter
    private var categoryName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[CategoryMealsViewModel::class.java]

        categoriesAdapter = CategoriesAdapter()

        categoryName = intent.getStringExtra(CATEGORY_NAME).toString()
        if (categoryName != null) {
            mealMvvm.getMealsByCategory(categoryName)
        }

        binding.tvCategory.text = categoryName
        prepareCategoryItemsRecyclerView()
        observerCategoryDetailLivedData()
        onCategoryItemClick()

    }

    private fun prepareCategoryItemsRecyclerView() {
        binding.rvCategoryNames.apply {
            layoutManager = LinearLayoutManager(this@CategoryDetailActivity, LinearLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    /*private fun observerCategoryDetailLivedData(){
        mealMvvm.observerCategoryLiveData().observe(this,
            { mealList ->
                categoriesAdapter.setCategoryMeals(mealsList = mealList as ArrayList <CategoryMeals>)
            })
    }*/

    private fun observerCategoryDetailLivedData() {
        mealMvvm.observerCategoryLiveData().observe(this) { mealList ->
            categoriesAdapter.setCategoryMeals(ArrayList(mealList))
        }
    }

    private fun onCategoryItemClick() {
        categoriesAdapter.onItemClick = { meal ->
            val intent = Intent(this,MealActivity :: class.java)
            intent.putExtra(HomeFragment.MEAL_ID, meal.idMeal)
            intent.putExtra(HomeFragment.MEAL_NAME, meal.strMeal)
            intent.putExtra(HomeFragment.MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)

        }
    }
}
