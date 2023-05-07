package com.example.btl_app_android.listeners

import com.example.btl_app_android.models.RecipeDetailsResponse

interface DetailsRecipeListener {
    fun didFetch(response: RecipeDetailsResponse?, message: String?)
    fun didError(message: String?)
}