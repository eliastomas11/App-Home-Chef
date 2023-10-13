package com.example.chefgram.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.domain.model.FilterParams
import com.example.chefgram.domain.model.Recipe
import com.example.chefgram.domain.model.RecipeIngredient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class FavoriteViewModel @Inject constructor(private val repository: MealsRepository) : ViewModel() {

    private val _favoriteRecipeList = MutableLiveData<List<Recipe>>()
    val favoriteRecipeList: LiveData<List<Recipe>> get() = _favoriteRecipeList

    init {
        getFavoritesRecipes()
    }

    private fun getFavoritesRecipes() {
        viewModelScope.launch {
            _favoriteRecipeList.value = repository.getFavoriteRecipes()
        }
    }

    fun filterRecipes(filterParams: FilterParams) {
        if (filterParams.isCreatedByUser) {
            filterByCreatedByUser(filterParams)
        }
        if (filterParams.exclusive) {
            filterExclusive(filterParams.ingredient)
        }else{
            filterInclusive(filterParams.ingredient)
        }
    }

    private fun filterByCreatedByUser(filterParams: FilterParams) {
        viewModelScope.launch {
            _favoriteRecipeList.value?.filter {
                it.createdBy == "user"
            }
        }
    }

    private fun filterExclusive(filterIngredients: List<RecipeIngredient>) {
        val exclusiveList = mutableSetOf<Recipe>()
        _favoriteRecipeList.value!!.filterTo(exclusiveList) {
            it.ingredients.containsAll(filterIngredients)
        }
        _favoriteRecipeList.value = exclusiveList.toList()
    }

    private fun filterInclusive(filterIngredients: List<RecipeIngredient>) {
        val abarcativeList = mutableSetOf<Recipe>()
        for (ingredient in filterIngredients) {
            _favoriteRecipeList.value!!.filterTo(abarcativeList) {
                it.ingredients.contains(ingredient)
            }
        }
        _favoriteRecipeList.value = abarcativeList.toList()
    }
}