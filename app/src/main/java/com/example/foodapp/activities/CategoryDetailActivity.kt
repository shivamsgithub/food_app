package com.example.foodapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapters.CategoriesAdapter
import com.example.foodapp.databinding.ActivityCategoryDetailBinding
import com.example.foodapp.models.CategoryMeals
import com.example.foodapp.viewModel.CategoryMealsViewModel

class CategoryDetailActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var mealMvvm: CategoryMealsViewModel
    private lateinit var categoriesAdapter : CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[CategoryMealsViewModel::class.java]

        categoriesAdapter = CategoriesAdapter()

        prepareCategoryItemsRecyclerView()
        observerCategoryDetailLivedData()

    }

    private fun prepareCategoryItemsRecyclerView() {
        binding.rvCategoryNames.apply {
            layoutManager = LinearLayoutManager(this@CategoryDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observerCategoryDetailLivedData(){
        mealMvvm.observerCategoryLiveData().observe(this,
            { mealList ->
                categoriesAdapter.setCategoryMeals(mealsList = mealList as ArrayList <CategoryMeals>)
            })
    }
}
