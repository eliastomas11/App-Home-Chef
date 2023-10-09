package com.example.chefgram.common

import com.example.chefgram.data.mealremotemodel.MealDto
import com.example.chefgram.data.mealremotemodel.MealResponse
import com.example.chefgram.ui.model.Meal
import com.example.chefgram.data.repository.local.MealEntity

fun MealResponse.toMealDto(): List<MealDto> = this.results.map { MealDto(it.id,it.name,it.description,it.thumbnail_url,it.country,it.num_servings,it.cook_time_minutes) }

fun MealDto.toMeal(): Meal = Meal(this.name?:"",this.description?:"",this.thumbnail_url?:"")

fun MealDto.toMealEntity(): MealEntity = MealEntity(this.id,this.name!!,this.description!!,this.thumbnail_url!!)

fun MealEntity.toMealDto(): MealDto = MealDto(this.id,this.name,this.description,this.thumbnail_url,this.country,this.num_servings,this.cook_time_minutes)

