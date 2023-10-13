package com.example.chefgram.data.mealremotemodel

import com.example.chefgram.data.mealremotemodel.unusedmodel.Measures

data class ExtendedIngredient(
    val aisle: String,
    val amount: Double,
    val id: Int,
    val image: String,
    val name: String,
    val originalName: String,
    val unit: String
)