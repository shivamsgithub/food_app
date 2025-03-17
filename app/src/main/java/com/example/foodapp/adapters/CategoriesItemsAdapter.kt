package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.PopularItemsBinding
import com.example.foodapp.models.CategoryRealData

class CategoriesItemsAdapter(): RecyclerView.Adapter<CategoriesItemsAdapter.CategoryMealViewHolder>() {

    private var mealLIst = ArrayList<CategoryRealData>()
    lateinit var onItemClick : ((CategoryRealData) -> Unit)

    fun setCategoryMeals(mealsList:ArrayList<CategoryRealData>){
        this.mealLIst = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealViewHolder {
        return CategoryMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return  mealLIst.size
    }

    override fun onBindViewHolder(holder: CategoryMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealLIst[position].strCategoryThumb)
            .into(holder.binding.ivPopularMeal)

        holder.binding.tvMealName.text  = mealLIst[position].strCategory

        holder.itemView.setOnClickListener{
            onItemClick.invoke(mealLIst[position])
        }
    }

    class CategoryMealViewHolder(val binding: PopularItemsBinding): RecyclerView.ViewHolder(binding.root)
}