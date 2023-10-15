package com.example.chefgram.data.repository.remote.recipemodel

import com.squareup.moshi.Json


data class RecipeResponse(
    val recipes: List<RecipeResponseDto>
)