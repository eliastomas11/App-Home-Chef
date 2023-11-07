package com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel

import com.squareup.moshi.Json

@Json(name = "extendedIngredients")
data class IngredientDto(
    val aisle: String,
    val amount: Double,
    val id: Int,
    val image: String,
    val name: String,
    val originalName: String,
    val unit: String
)