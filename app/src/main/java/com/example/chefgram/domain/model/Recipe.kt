package com.example.chefgram.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val ingredients: List<RecipeIngredient>,
    val numServings: Int,
    val cookingTime: Int,
    val vegan: Boolean,
    val dairyFree: Boolean,
    val glutenFree: Boolean,
    val veryHealthy: Boolean,
    val isSaved: Boolean = false,
    val createdBy: String = "Unknown"
)
