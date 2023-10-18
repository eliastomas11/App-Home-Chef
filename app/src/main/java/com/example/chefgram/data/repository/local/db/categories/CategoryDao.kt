package com.example.chefgram.data.repository.local.db.categories

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category LIMIT 50")
    fun getAllCategories(): List<CategoryEntity>
}