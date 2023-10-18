package com.example.chefgram.domain.model

enum class Categories {
    INGREDIENT,
    CREATEDBY;
    override fun toString(): String {
        return when(this) {
            INGREDIENT -> "Ingredient"
            CREATEDBY -> "CreatedBy"
        }
    }
}