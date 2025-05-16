package com.example.foodapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.activities.CategoryDetailActivity
import com.example.foodapp.activities.MainActivity
import com.example.foodapp.activities.MealActivity
import com.example.foodapp.adapters.CategoriesItemsAdapter
import com.example.foodapp.adapters.MostPopularItemsAdapter
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.models.CategoryMeals
import com.example.foodapp.models.CategoryRealData
import com.example.foodapp.models.Meal
import com.example.foodapp.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private  lateinit var  binding: FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var randomMeal : Meal
    private lateinit var popularItemsAdapter : MostPopularItemsAdapter
    private lateinit var categoriesItemsAdapter : CategoriesItemsAdapter

    companion object{
        const val MEAL_ID = "package com.example.foodapp.fragment.idMeal"
        const val MEAL_NAME = "package com.example.foodapp.fragment.nameMeal"
        const val MEAL_THUMB = "package com.example.foodapp.fragment.thumbMeal"
        const val CATEGORY_NAME = "package com.example.foodapp.fragment.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        popularItemsAdapter = MostPopularItemsAdapter()
        categoriesItemsAdapter = CategoriesItemsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()
        prepareCategoriesItemsRecyclerView()

        viewModel.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        viewModel.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()

        viewModel.getCategories()
        observeCategoriesItemsLiveData()
        onCategoryClick()
    }

    private fun onCategoryClick() {
        categoriesItemsAdapter.onItemClick = {category ->
            val intent = Intent(activity, CategoryDetailActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { meal ->
            val intent = Intent(activity,MealActivity :: class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)

        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.rvPopularMeals.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    // converted to lambda function by system or (alt + shift + enter)
    private fun observePopularItemsLiveData() {
        viewModel.observePopularItemsLivedata().observe(viewLifecycleOwner,
            { mealList ->
                popularItemsAdapter.setMeals(mealsList = mealList as ArrayList <CategoryMeals>)
            })
    }

    private fun prepareCategoriesItemsRecyclerView() {
        binding.rvCategoriesHome.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesItemsAdapter
        }
    }

    private fun observeCategoriesItemsLiveData() {
        viewModel.observeCategoryItemsLivedata().observe(viewLifecycleOwner,
            { mealList ->
                categoriesItemsAdapter.setCategoryMeals(mealsList = mealList as ArrayList <CategoryRealData>)
            })
    }

    private fun observerRandomMeal(){
//        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner, object : Observer<Meal> {
            /*override fun onChanged(t: Meal?) {
                Glide.with(this@HomeFragment)
                    .load(t!!.strMealThumb)
                    .into(binding.ivMainHome)
            }*/

        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner,
            { meal ->
                Glide.with(this@HomeFragment)
                    .load(meal!!.strMealThumb)
                    .into(binding.ivMainHome)

                this.randomMeal = meal

        })
    }

    private  fun onRandomMealClick(){
        binding.cvRandomMeal.setOnClickListener {

            val intent = Intent(activity, MealActivity:: class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }
}