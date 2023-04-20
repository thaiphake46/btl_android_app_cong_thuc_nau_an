package com.example.btl_app_android.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.R
import com.example.btl_app_android.models.Recipe
import com.squareup.picasso.Picasso

<<<<<<< HEAD
class RandomRecipeAdapter(var context: Context, var list: List<Recipe>) :
=======
class RandomRecipeAdapter(var context: Context, private var list: List<Recipe>) :
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
    RecyclerView.Adapter<RandomRecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomRecipeViewHolder {
        return RandomRecipeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RandomRecipeViewHolder, position: Int) {
        holder.txt_title.text = list[position].title
        holder.txt_title.isSelected = true
        holder.txt_likes.text = list[position].aggregateLikes.toString() + " Likes"
        holder.txt_servings.text = list[position].servings.toString() + " Servings"
        holder.txt_times.text = list[position].readyInMinutes.toString() + " Minutes"
        Picasso.get().load(list[position].image).into(holder.img_food)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class RandomRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var random_list_container: CardView
    var txt_title: TextView
    var img_food: ImageView
    var txt_servings: TextView
    var txt_likes: TextView
    var txt_times: TextView

    init {
        random_list_container = itemView.findViewById(R.id.random_list_container)
        txt_title = itemView.findViewById(R.id.txt_title)
        img_food = itemView.findViewById(R.id.img_food)
        txt_servings = itemView.findViewById(R.id.txt_servings)
        txt_likes = itemView.findViewById(R.id.txt_likes)
        txt_times = itemView.findViewById(R.id.txt_times)
    }
}