package com.example.btl_app_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_app_android.adapters.IngredientsAdapter
import com.example.btl_app_android.dialogs.ProgressDialog
import com.example.btl_app_android.listeners.DetailsRecipeListener
import com.example.btl_app_android.models.RecipeDetailsResponse
import com.squareup.picasso.Picasso

class RecipeDetailActivity : AppCompatActivity() {
    lateinit var id: String
    lateinit var textView_meal_name: TextView
    lateinit var textView_meal_source: TextView
    lateinit var imageView_meal_image: ImageView
    lateinit var textView_meal_summary: TextView
    lateinit var recycler_meal_ingredients: RecyclerView
    lateinit var ingredientsAdapter: IngredientsAdapter

    lateinit var manager: RequestManager
    lateinit var builderDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        findView()
        id = intent.getStringExtra("id").toString()
        manager = RequestManager(this)
        manager.getDetailsRecipe(recipeDetailsListener, id)
        builderDialog = ProgressDialog(this, "Loading ...")
        builderDialog.show()
    }

    private fun findView() {
        textView_meal_name = findViewById(R.id.textView_meal_name)
        textView_meal_source = findViewById(R.id.textView_meal_source)
        imageView_meal_image = findViewById(R.id.imageView_meal_image)
        textView_meal_summary = findViewById(R.id.textView_meal_summary)
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients)
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
}