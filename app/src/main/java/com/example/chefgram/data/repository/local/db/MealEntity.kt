package com.example.chefgram.data.repository.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MealEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val thumbnail_url: String,
    val country: String = "",
    val num_servings: Int = 0,
    val cook_time_minutes: Int = 0/*,
    val ingredients: List<Ingredient>,*/
)
