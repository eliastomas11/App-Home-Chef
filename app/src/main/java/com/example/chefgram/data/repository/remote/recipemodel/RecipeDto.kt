package com.example.chefgram.data.repository.remote.recipemodel

data class RecipeDto(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val numServings: Int = 0,
    val cookTimeMinutes: Int = 0,
    val ingredient: List<IngredientDto>,
    val veganFree:Boolean,
    val dairyFree:Boolean,
    val glutenFree:Boolean,
    val createdBy:String,
    val veryHealthy:Boolean,
)
