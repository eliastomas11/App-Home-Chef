package com.example.chefgram.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class FavoriteViewModel @Inject constructor(private val repository: MealsRepository): ViewModel() {

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
}