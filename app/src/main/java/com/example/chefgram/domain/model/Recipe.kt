package com.example.chefgram.domain.model

data class Recipe(val id:Int, val title: String, val description: String, val image: String, val country: String, val num_servings: Int, val cook_time_minutes: Int, val ingredients:List<RecipeIngredient>, val isSaved: Boolean = false)
