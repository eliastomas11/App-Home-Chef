package com.example.chefgram.data.repository.remote.recipemodel

data class IngredientDto(
    val id: Int,
    val name: String,
    val originalName: String,
    val amount: Double,
    val unit: String,
    val image: String,
    val aisle: String
)