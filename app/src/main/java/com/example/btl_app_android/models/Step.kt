package com.example.btl_app_android.models

import com.example.btl_app_android.models.Ingredient
import com.example.btl_app_android.models.Length
import java.util.ArrayList

class Step {
    var number = 0
    var step: String? = null
    var ingredients: ArrayList<Ingredient>? = null
    var equipment: ArrayList<Any>? = null
    var length: Length? = null
}