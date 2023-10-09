package com.example.chefgram.data.mealremotemodel

data class Result(
    val cook_time_minutes: Int?,
    val country: String?,
    val description: String?,
    val id: Int,
    val name: String?,
    val num_servings: Int?,
    val thumbnail_url: String?,
)