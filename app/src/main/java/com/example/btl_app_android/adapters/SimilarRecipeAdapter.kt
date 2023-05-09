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
import com.example.btl_app_android.listeners.RecipeClickListener
import com.example.btl_app_android.models.SimilarRecipesResponse
import com.squareup.picasso.Picasso

class SimilarRecipeAdapter(
    val context: Context,
    val list: List<SimilarRecipesResponse>,
    var listener: RecipeClickListener
) :
    RecyclerView.Adapter<SimilarRecipeAdapter.SimilarRecipeViewHolder>() {
    inner class SimilarRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val similar_recipe_holder: CardView = itemView.findViewById(R.id.similar_recipe_holder)
        val textView_similar_title: TextView = itemView.findViewById(R.id.textView_similar_title)
        val imageView_similar: ImageView = itemView.findViewById(R.id.imageView_similar)
        val textView_similar_servings: TextView =
            itemView.findViewById((R.id.textView_similar_servings))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarRecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false)
        return SimilarRecipeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SimilarRecipeViewHolder, position: Int) {
        holder.textView_similar_title.text = list[position].title
        holder.textView_similar_title.isSelected = true
        holder.textView_similar_servings.text = "${list[position].servings} Person"
        Picasso.get()
            .load("https://spoonacular.com/recipeImages/${list[position].id}-556x370.${list[position].imageType}")
            .into(holder.imageView_similar)
        holder.similar_recipe_holder.setOnClickListener {
            listener.onRecipeClicked(list[position].id.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
