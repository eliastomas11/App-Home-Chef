package com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipewithingredient

import androidx.room.Embedded
import androidx.room.Relation
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient.IngredientEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipe.RecipeEntity

data class RecipeWithIngredient(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id",
    )
    val ingredientList: List<IngredientEntity>

)
