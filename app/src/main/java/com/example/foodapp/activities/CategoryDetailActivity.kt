package com.example.foodapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapters.CategoriesAdapter
import com.example.foodapp.adapters.CategoriesItemsAdapter
import com.example.foodapp.databinding.ActivityCategoryDetailBinding
import com.example.foodapp.databinding.ActivityMealBinding
import com.example.foodapp.databinding.FragmentCategoryBinding
import com.example.foodapp.fragment.HomeFragment
import com.example.foodapp.models.CategoryMeals
import com.example.foodapp.models.Meal
import com.example.foodapp.viewModel.CategoryMealsViewModel
import com.example.foodapp.viewModel.HomeViewModel
import com.example.foodapp.viewModel.MealViewModel

class CategoryDetailActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var mealMvvm: CategoryMealsViewModel
    private lateinit var categoriesItemsAdapter : CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_category_detail)

        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[CategoryMealsViewModel::class.java]
        categoriesItemsAdapter = CategoriesAdapter()

        val categoryName = intent.getStringExtra(HomeFragment.CATEGORY_NAME)
        prepareCategoryItemsRecyclerView()
        if (categoryName != null) {
            mealMvvm.getMealsByCategory(categoryName)
        }
        observerCategoryDetailLivedData()

    }

    private fun prepareCategoryItemsRecyclerView() {
        binding.rvCategoryNames.apply {
            layoutManager = LinearLayoutManager(this@CategoryDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesItemsAdapter
        }
    }

    private fun observerCategoryDetailLivedData(){
        mealMvvm.observerCategoryLiveData().observe(this,
            { mealList ->
                categoriesItemsAdapter.setCategoryMeals(mealsList = mealList as ArrayList <CategoryMeals>)
            })
    }
}
