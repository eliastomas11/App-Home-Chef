package com.example.chefgram.ui.main

import com.example.chefgram.data.mealremotemodel.Ingredient
import com.example.chefgram.domain.model.RecipeIngredient

data class FilterParams(val ingredient: List<RecipeIngredient>,val exclusive: Boolean = false,val isFavorite: Boolean = false)
