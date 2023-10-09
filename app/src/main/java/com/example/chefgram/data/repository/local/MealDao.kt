package com.example.chefgram.data.repository.local

interface MealDao  {
    fun saveToFavorites(meal: MealEntity): Boolean
    fun getFavorites(): List<MealEntity>
    fun deleteFromFavorites(meal: MealEntity): Boolean

}
