package com.example.chefgram.data.repository.remote.recipemodel

data class RecipeResponseDto(
    val id: Int,
    val title: String,
    val summary: String,
    val extendedIngredients: List<IngredientDto>,
    val image: String,
    val readyInMinutes: Int,
    val dairyFree: Boolean,
    val glutenFree: Boolean,
    val vegan: Boolean,
    val sourceName: String,
    val cuisines: List<Cuisine>,
    val diets: List<String>,
    val dishTypes: List<String>,
    val instructions: String,
    val occasions: List<String>,
    val servings: Int,
    val sustainable: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
)