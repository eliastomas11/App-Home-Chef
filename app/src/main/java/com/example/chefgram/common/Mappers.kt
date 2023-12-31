package com.example.chefgram.common


import com.example.chefgram.data.repository.reciperepo.category.Categories
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.ingredientcache.IngredientCacheEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipecache.RecipeCacheEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipewithingredientcache.RecipeWithIngredientCache
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient.IngredientEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipe.RecipeEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipewithingredient.RecipeWithIngredient
import com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel.IngredientDto
import com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel.RecipeDto
import com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel.RecipeResponseDto
import com.example.chefgram.domain.model.Recipe
import com.example.chefgram.domain.model.RecipeIngredient
import com.example.chefgram.ui.main.adapters.FilterItem

fun RecipeResponseDto.toRecipeDto(): RecipeDto =
    RecipeDto(
        id = this.id,
        name = this.title,
        description = this.summary,
        image = this.image,
        numServings = this.servings,
        cookTimeMinutes = this.readyInMinutes,
        ingredient = this.extendedIngredients,
        veganFree = this.vegan,
        dairyFree = this.dairyFree,
        glutenFree = this.glutenFree,
        veryHealthy = this.veryHealthy,
        createdBy = this.sourceName
    )


fun RecipeDto.toRecipeEntity(): RecipeEntity =
    RecipeEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image ?: "",
        numServings = this.numServings,
        cookTimeMinutes = this.cookTimeMinutes,
        veganFree = this.veganFree,
        glutenFree = this.glutenFree,
        dairyFree = this.dairyFree,
        veryHealthy = this.veryHealthy,
        createdBy = this.createdBy
    )

fun RecipeDto.toRecipe(): Recipe =
    Recipe(
        id = this.id,
        title = this.name,
        description = this.description,
        image = this.image ?: "",
        ingredients = this.ingredient.map { it.toRecipeIngredient() },
        numServings = this.numServings,
        cookingTime = this.cookTimeMinutes,
        createdBy = this.createdBy,
        vegan = this.veganFree,
        dairyFree = this.dairyFree,
        glutenFree = this.glutenFree,
        veryHealthy = this.veryHealthy
    )


fun Recipe.toRecipeDto(): RecipeDto =
    RecipeDto(
        id = this.id,
        name = this.title,
        description = this.description,
        image = this.image,
        numServings = this.numServings,
        cookTimeMinutes = this.cookingTime,
        veganFree = this.vegan,
        dairyFree = this.dairyFree,
        glutenFree = this.glutenFree,
        veryHealthy = this.veryHealthy,
        createdBy = this.createdBy,
        ingredient = this.ingredients.map { it.toIngredientDto() }
    )

fun IngredientDto.toRecipeIngredient(): RecipeIngredient = RecipeIngredient(
    id = this.id,
    name = this.name,
    amount = this.amount,
    unit = this.unit,
    image = this.image,
    type = this.aisle,
    originalName = this.originalName
)

fun RecipeIngredient.toIngredientDto(): IngredientDto = IngredientDto(
    id = this.id,
    name = this.name,
    amount = this.amount,
    unit = this.unit,
    image = this.image ?: "",
    aisle = this.type ?: "",
    originalName = this.originalName,
)

fun IngredientDto.toIngredientEntity(): IngredientEntity = IngredientEntity(
    id = this.id,
    name = this.name,
    amount = this.amount,
    unit = this.unit,
    image = this.image,
    type = this.aisle,
    originalName = this.originalName,
    recipeId = 0
)

fun IngredientEntity.toIngredientDto(): IngredientDto = IngredientDto(
    id = this.id,
    name = this.name,
    originalName = this.originalName,
    amount = this.amount,
    unit = this.unit,
    aisle = this.type,
    image = this.image
)

fun IngredientDto.toIngredientCache(): IngredientCacheEntity = IngredientCacheEntity(
    id = this.id,
    name = this.name,
    originalName = this.originalName,
    amount = this.amount,
    unit = this.unit,
    type = this.aisle,
    image = this.image,
    recipeCacheId = 0
)

fun IngredientCacheEntity.toIngredientDto(): IngredientDto = IngredientDto(
    id = this.id,
    name = this.name,
    originalName = this.originalName,
    amount = this.amount,
    unit = this.unit,
    aisle = this.type ?: "",
    image = this.image ?: ""
)

fun RecipeWithIngredientCache.toRecipeDto(): RecipeDto =
    RecipeDto(
        id = this.recipe.id,
        name = this.recipe.name,
        description = this.recipe.description,
        image = this.recipe.image,
        numServings = this.recipe.numServings,
        cookTimeMinutes = this.recipe.cookTimeMinutes,
        veganFree = this.recipe.veganFree,
        dairyFree = this.recipe.dairyFree,
        glutenFree = this.recipe.glutenFree,
        veryHealthy = this.recipe.veryHealthy,
        createdBy = this.recipe.createdBy,
        ingredient = this.ingredientList.map { it.toIngredientDto() },
    )


fun RecipeWithIngredient.toRecipeDto(): RecipeDto =
    RecipeDto(
        id = this.recipe.id,
        name = this.recipe.name,
        description = this.recipe.description,
        image = this.recipe.image,
        numServings = this.recipe.numServings,
        cookTimeMinutes = this.recipe.cookTimeMinutes,
        veganFree = this.recipe.veganFree,
        glutenFree = this.recipe.glutenFree,
        dairyFree = this.recipe.dairyFree,
        veryHealthy = this.recipe.veryHealthy,
        createdBy = this.recipe.createdBy,
        ingredient = this.ingredientList.map { it.toIngredientDto() },
    )

fun RecipeDto.toRecipeCache(): RecipeCacheEntity =
    RecipeCacheEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image ?: "",
        numServings = this.numServings,
        cookTimeMinutes = this.cookTimeMinutes,
        veganFree = this.veganFree,
        glutenFree = this.glutenFree,
        dairyFree = this.dairyFree,
        veryHealthy = this.veryHealthy,
        createdBy = this.createdBy,
    )



fun Categories.toFilterItem(): FilterItem =
    FilterItem(category = this)

