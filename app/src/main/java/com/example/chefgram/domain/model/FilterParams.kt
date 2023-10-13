package com.example.chefgram.domain.model

data class FilterParams(val ingredient: List<RecipeIngredient>,val exclusive: Boolean = false,val isFavorite: Boolean = false,val isCreatedByUser: Boolean = false)
