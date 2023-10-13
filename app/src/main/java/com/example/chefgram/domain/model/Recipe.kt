package com.example.chefgram.domain.model

data class Recipe(val id:Int, val title: String, val description: String, val image: String, val ingredients:List<RecipeIngredient>,val country: String = "Unknown", val isSaved: Boolean = false, val createdBy: String = "Unknown")
