package com.example.btl_app_android.listeners

import com.example.btl_app_android.models.SimilarRecipesResponse

interface SimilarRecipeListener {
    fun didFetch(response: List<SimilarRecipesResponse>?, message: String?)
    fun didError(message: String?)
}