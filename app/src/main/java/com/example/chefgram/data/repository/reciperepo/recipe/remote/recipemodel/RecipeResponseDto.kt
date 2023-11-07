package com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel


data class RecipeResponseDto(
    val cookingMinutes: Int,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<IngredientDto>,
    val glutenFree: Boolean,
    val id: Int,
    val image: String,
    val instructions: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
)