package com.example.btl_app_android.models

import com.example.btl_app_android.models.AnalyzedInstruction
import com.example.btl_app_android.models.ExtendedIngredient
import java.util.ArrayList

class Recipe {
    var vegetarian = false
    var vegan = false
    var glutenFree = false
    var dairyFree = false
    var veryHealthy = false
    var cheap = false
    var veryPopular = false
    var sustainable = false
    var lowFodmap = false
    var weightWatcherSmartPoints = 0
    var gaps: String? = null
    var preparationMinutes = 0
    var cookingMinutes = 0
    var aggregateLikes = 0
    var healthScore = 0
    var creditsText: String? = null
    var license: String? = null
    var sourceName: String? = null
    var pricePerServing = 0.0
    var extendedIngredients: ArrayList<ExtendedIngredient>? = null
    var id = 0
    var title: String? = null
    var readyInMinutes = 0
    var servings = 0
    var sourceUrl: String? = null
    var image: String? = null
    var imageType: String? = null
    var summary: String? = null
    var cuisines: ArrayList<Any>? = null
    var dishTypes: ArrayList<String>? = null
    var diets: ArrayList<String>? = null
    var occasions: ArrayList<Any>? = null
    var instructions: String? = null
    var analyzedInstructions: ArrayList<AnalyzedInstruction>? = null
    var originalId: Any? = null
    var spoonacularSourceUrl: String? = null
}