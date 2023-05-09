package com.example.btl_app_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.adapters.IngredientsAdapter
import com.example.btl_app_android.adapters.SimilarRecipeAdapter
import com.example.btl_app_android.dialogs.ProgressDialog
import com.example.btl_app_android.listeners.DetailsRecipeListener
import com.example.btl_app_android.listeners.RecipeClickListener
import com.example.btl_app_android.listeners.SimilarRecipeListener
import com.example.btl_app_android.models.RecipeDetailsResponse
import com.example.btl_app_android.models.SimilarRecipesResponse
import com.squareup.picasso.Picasso

class RecipeDetailActivity : AppCompatActivity() {
    lateinit var id: String
    lateinit var textView_meal_name: TextView
    lateinit var textView_meal_source: TextView
    lateinit var imageView_meal_image: ImageView
    lateinit var textView_meal_summary: TextView
    lateinit var recycler_meal_similar: RecyclerView
    lateinit var recycler_meal_ingredients: RecyclerView
    lateinit var ingredientsAdapter: IngredientsAdapter

    lateinit var manager: RequestManager
    lateinit var builderDialog: ProgressDialog

    lateinit var similarRecipeAdapter: SimilarRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        findView()
        id = intent.getStringExtra("id").toString()
        manager = RequestManager(this)
        manager.getDetailsRecipe(recipeDetailsListener, id)
        manager.getSimilarRecipes(similarRecipeListener, id)
        builderDialog = ProgressDialog(this, "Loading ...")
        builderDialog.show()
    }

    private fun findView() {
        textView_meal_name = findViewById(R.id.textView_meal_name)
        textView_meal_source = findViewById(R.id.textView_meal_source)
        imageView_meal_image = findViewById(R.id.imageView_meal_image)
        textView_meal_summary = findViewById(R.id.textView_meal_summary)
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients)

        recycler_meal_similar = findViewById(R.id.recycler_meal_similar)
    }

    private val recipeDetailsListener = object : DetailsRecipeListener {
        override fun didFetch(response: RecipeDetailsResponse?, message: String?) {
            builderDialog.dismiss()
            textView_meal_name.text = response!!.title
            textView_meal_source.text = response.sourceName
            textView_meal_summary.text = response.summary
            Picasso.get().load(response.image).into(imageView_meal_image)

            recycler_meal_ingredients.setHasFixedSize(true)
            recycler_meal_ingredients.layoutManager = LinearLayoutManager(
                this@RecipeDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            ingredientsAdapter =
                IngredientsAdapter(this@RecipeDetailActivity, response.extendedIngredients)
            recycler_meal_ingredients.adapter = ingredientsAdapter
        }

        override fun didError(message: String?) {
            Toast.makeText(this@RecipeDetailActivity, message, Toast.LENGTH_LONG).show()
        }
    }

    private val similarRecipeListener = object : SimilarRecipeListener {
        override fun didFetch(response: List<SimilarRecipesResponse>?, message: String?) {
            recycler_meal_similar.setHasFixedSize(true)
            recycler_meal_similar.layoutManager = LinearLayoutManager(
                this@RecipeDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            similarRecipeAdapter = SimilarRecipeAdapter(
                this@RecipeDetailActivity,
                response!!,
                recipeClickListener
            )
            recycler_meal_similar.adapter = similarRecipeAdapter
        }

        override fun didError(message: String?) {
            Toast.makeText(this@RecipeDetailActivity, message, Toast.LENGTH_LONG).show()
        }
    }

    private val recipeClickListener = object : RecipeClickListener {
        override fun onRecipeClicked(id: String) {
//            Toast.makeText(this@RecipeDetailActivity, id, Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this@RecipeDetailActivity, RecipeDetailActivity::class.java)
                    .putExtra("id", id)
            )
        }

    }
}