package com.example.chefgram.domain.model

data class RecipeIngredient(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String,
    val image: String,
    val type: String,
    val originalName: String
)
