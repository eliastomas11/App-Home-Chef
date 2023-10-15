package com.example.chefgram.data.repository.local.db.filteringredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filter_ingredient")
data class FilterIngredient(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id") @PrimaryKey val id: Int
)