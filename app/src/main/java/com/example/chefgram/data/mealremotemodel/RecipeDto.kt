package com.example.chefgram.data.mealremotemodel

data class RecipeDto(
    val id: Int,
    val name: String?,
    val description: String?,
    val thumbnail_url: String?,
    val num_servings: Int? = 0 ,
    val cook_time_minutes: Int? = 0,
    val ingredient: List<ExtendedIngredient>
)
