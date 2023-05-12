package com.example.btl_app_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R
import com.example.btl_app_android.models.Ingredient
import com.squareup.picasso.Picasso

class InstructionsIngredientsAdapter(var context: Context, var list: List<Ingredient>) :
    RecyclerView.Adapter<InstructionsIngredientsAdapter.InstructionsIngredientsViewHolder>() {

    inner class InstructionsIngredientsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView_step_items: ImageView = itemView.findViewById(R.id.imageView_step_items)
        var textView_step_items: TextView = itemView.findViewById((R.id.textView_step_items))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstructionsIngredientsViewHolder {
        return InstructionsIngredientsViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.list_instructions_step_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InstructionsIngredientsViewHolder, position: Int) {
        holder.textView_step_items.text = list[position].name
        holder.textView_step_items.isSelected = true
        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/${list[position].image}")
            .into(holder.imageView_step_items)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}