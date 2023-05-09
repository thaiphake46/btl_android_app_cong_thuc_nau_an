package com.example.btl_app_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R
import com.example.btl_app_android.models.ExtendedIngredient
import com.squareup.picasso.Picasso

class IngredientsAdapter(val context: Context, val list: List<ExtendedIngredient>) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_ingredients_quantity: TextView =
            itemView.findViewById(R.id.textView_ingredients_quantity)
        var imageView_ingredients: ImageView = itemView.findViewById(R.id.imageView_ingredients)
        var textView_ingredients_name: TextView =
            itemView.findViewById(R.id.textView_ingredients_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val ingredientsViewHolder =
            LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false)
        return IngredientsViewHolder(ingredientsViewHolder)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.textView_ingredients_name.text = list[position].name
        holder.textView_ingredients_name.isSelected = true
        holder.textView_ingredients_quantity.text = list[position].original
        holder.textView_ingredients_quantity.isSelected = true
        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/" + list[position].image)
            .into(holder.imageView_ingredients)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}