package com.example.foodapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.activities.CategoryDetailActivity
import com.example.foodapp.adapters.CategoriesItemsAdapter
import com.example.foodapp.databinding.FragmentCategoryBinding
import com.example.foodapp.fragment.HomeFragment.Companion.CATEGORY_NAME
import com.example.foodapp.models.CategoryRealData
import com.example.foodapp.viewModel.HomeViewModel


class CategoryFragment : Fragment() {

    private  lateinit var  binding: FragmentCategoryBinding
    private lateinit var homeMvvm : HomeViewModel
    private lateinit var categoriesItemsAdapter : CategoriesItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]

        categoriesItemsAdapter = CategoriesItemsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareCategoriesItemsRecyclerView()
        homeMvvm.getCategories()
        observeCategoriesItemsLiveData()
        onCategoryClick()
    }

    private fun prepareCategoriesItemsRecyclerView() {
        binding.rvCategoriesCategory.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = categoriesItemsAdapter
        }
    }

    private fun observeCategoriesItemsLiveData() {
        homeMvvm.observeCategoryItemsLivedata().observe(viewLifecycleOwner,
            { mealList ->
                categoriesItemsAdapter.setCategoryMeals(mealsList = mealList as ArrayList <CategoryRealData>)
            })
    }

    private fun onCategoryClick() {
        categoriesItemsAdapter.onItemClick = {category ->
            val intent = Intent(activity, CategoryDetailActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }
}