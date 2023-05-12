package com.example.btl_app_android.listeners

import com.example.btl_app_android.models.InstructionsRecipeResponse

interface InstructionsListener {
    fun didFetch(response: List<InstructionsRecipeResponse>?, message: String?)
    fun didError(message: String?)
}