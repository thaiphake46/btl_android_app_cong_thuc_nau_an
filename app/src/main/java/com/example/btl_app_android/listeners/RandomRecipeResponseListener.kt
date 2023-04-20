package com.example.btl_app_android.listeners

import com.example.btl_app_android.models.RandomRecipeApiResponse

interface RandomRecipeResponseListener {
    fun didFetch(response: RandomRecipeApiResponse?, message: String?)
    fun didError(message: String?)
}