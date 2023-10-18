package com.example.chefgram.domain.model

data class IngredientFilterItem(override val name:String, override val id: Int) : FilterListContent(id,name)
