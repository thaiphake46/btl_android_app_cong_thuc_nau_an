package com.example.myapplication.models

import com.example.myapplication.models.Ingredient
import com.example.myapplication.models.ExtendedIngredient
import com.example.myapplication.models.AnalyzedInstruction
import com.example.myapplication.models.Us
import com.example.myapplication.models.Metric
import com.example.myapplication.models.Measures
import java.util.ArrayList

class ExtendedIngredient {
    var id = 0
    var aisle: String? = null
    var image: String? = null
    var consistency: String? = null
    var name: String? = null
    var nameClean: String? = null
    var original: String? = null
    var originalName: String? = null
    var amount = 0.0
    var unit: String? = null
    var meta: ArrayList<String>? = null
    var measures: Measures? = null
}