package com.example.chefgram.domain.model

sealed class Category(open val catName: Categories, open val contentList: List<FilterListContent>){

    data class IngredientCategory(override val catName: Categories, override val contentList: List<IngredientFilterItem>): Category(catName,contentList)
}
