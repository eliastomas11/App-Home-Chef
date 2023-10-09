package com.example.chefgram.data.repository.local.db

import com.example.chefgram.data.repository.local.MealEntity

class FakeMealDao : MealDao {
    override fun saveToFavorites(meal: MealEntity): Boolean {
        return true

    }

    override fun getFavorites(): List<MealEntity> {
        return emptyList()
    }

    override fun deleteFromFavorites(meal: MealEntity): Boolean {
        return true
    }
}