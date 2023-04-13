package com.example.myapplication.models

import com.example.myapplication.models.Ingredient
import com.example.myapplication.models.ExtendedIngredient
import com.example.myapplication.models.AnalyzedInstruction
import com.example.myapplication.models.Us
import com.example.myapplication.models.Metric
import com.example.myapplication.models.Measures
import java.util.ArrayList

class Step {
    var number = 0
    var step: String? = null
    var ingredients: ArrayList<Ingredient>? = null
    var equipment: ArrayList<Any>? = null
    var length: Length? = null
}