package com.example.chefgram.common

import com.example.chefgram.data.mealremotemodel.MealDto
import com.example.chefgram.data.repository.local.db.MealEntity
import com.example.chefgram.data.repository.local.db.FavoriteRecipeEntity
import com.example.chefgram.domain.model.Recipe
import retrofit2.Response

fun MealResponse.toMealDto(): List<MealDto> = this.results.map {
    MealDto(
        it.id,
        it.name,
        it.description,
        it.thumbnail_url,
        it.country,
        it.num_servings,
        it.cook_time_minutes
        //it.ingredient
    )
}

fun MealDto.toMeal(): Recipe = Recipe(
    this.id,
    this.name ?: "",
    this.description ?: "",
    this.thumbnail_url ?: "",
    this.country ?: "",
    this.num_servings ?: 0,
    this.cook_time_minutes ?: 0,
    emptyList()
)

fun MealDto.toMealEntity(): MealEntity =
    MealEntity(this.id, this.name!!, this.description!!, this.thumbnail_url!!,this.country?:"",this.num_servings?:0,this.cook_time_minutes?:0//,this.ingredient?: emptyList())
    )

fun MealEntity.toMealDto(): MealDto = MealDto(
    this.id,
    this.name,
    this.description,
    this.thumbnail_url,
    this.country,
    this.num_servings,
    this.cook_time_minutes,
    //this.ingredients
)

fun Recipe.toRecipeDto(): MealDto = MealDto(
    this.id,
    this.title,
    this.description,
    this.image,
    this.country,
    this.num_servings,
    this.cook_time_minutes,
    //emptyList()
)

fun MealDto.toFavoriteRecipeEntity(): FavoriteRecipeEntity = FavoriteRecipeEntity(
    this.id,
    this.name!!,
    this.description!!,
    this.thumbnail_url!!,
    this.country ?: "",
    this.num_servings ?: 0,
    this.cook_time_minutes ?: 0
)

fun FavoriteRecipeEntity.toMealDto(): MealDto = MealDto(
    this.id, this.name, this.description,
    this.thumbnail_url, this.country, this.num_servings, this.cook_time_minutes//, emptyList()
)

fun Response<MealResponse>.toMealDto(): List<MealDto> = this.body()?.results?.map { MealDto(it.id, it.name, it.description,it.thumbnail_url, it.cook_time_minutes, it.ingredients, it.country, it.createdBy) } ?: emptyList()
