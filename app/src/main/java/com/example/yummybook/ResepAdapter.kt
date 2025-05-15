package com.example.yummybook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ResepAdapter(private val meals: List<MealsItem>) :
    RecyclerView.Adapter<ResepAdapter.MealViewHolder>() {

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealName: TextView = itemView.findViewById(R.id.tvMealName)
        val mealImage: ImageView = itemView.findViewById(R.id.ivMealImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.mealName.text = meal.strMeal
        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.mealImage)

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("MEAL_ID", meal.idMeal)

            val detailFragment = DetailResepFragment()
            detailFragment.arguments = bundle

            val activity = holder.itemView.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment) // pastikan kamu punya container di MainActivity
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount() = meals.size


}
