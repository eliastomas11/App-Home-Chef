package com.example.chefgram.data.mealremotemodel.unusedmodel

import com.example.chefgram.data.mealremotemodel.Equipment
import com.example.chefgram.data.mealremotemodel.Ingredient
import com.example.chefgram.data.mealremotemodel.Length

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)