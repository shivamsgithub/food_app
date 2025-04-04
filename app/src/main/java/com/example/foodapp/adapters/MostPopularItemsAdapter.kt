package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.PopularItemsBinding
import com.example.foodapp.models.CategoryMeals
import com.example.foodapp.models.MealsList

class MostPopularItemsAdapter(): RecyclerView.Adapter<MostPopularItemsAdapter.PopularMealViewHolder>() {

    private var mealLIst = ArrayList<CategoryMeals>()
    lateinit var onItemClick : ((CategoryMeals) -> Unit)

    fun setMeals(mealsList:ArrayList<CategoryMeals>){
        this.mealLIst = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return  mealLIst.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealLIst[position].strMealThumb)
            .into(holder.binding.ivPopularMeal)

        holder.binding.tvMealName.text = mealLIst[position].strMeal

        holder.itemView.setOnClickListener{
            onItemClick.invoke(mealLIst[position])
        }
    }

    class PopularMealViewHolder(val binding: PopularItemsBinding):RecyclerView.ViewHolder(binding.root)
}