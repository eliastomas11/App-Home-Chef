package com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel.unusedmodel


data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)