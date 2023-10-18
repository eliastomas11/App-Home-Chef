package com.example.chefgram.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.data.repository.RecipeRepository
import com.example.chefgram.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class FavoriteViewModel @Inject constructor(private val repository: RecipeRepository) :
    ViewModel() {

    private val _favoriteRecipeList = MutableLiveData<List<Recipe>>()
    val favoriteRecipeList: LiveData<List<Recipe>> get() = _favoriteRecipeList

    private val _recipeSelected = MutableLiveData<Recipe>()
    val recipeSelected: LiveData<Recipe> get() = _recipeSelected

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        getFavoritesRecipes()
    }

    private fun getFavoritesRecipes() {
        viewModelScope.launch {
            _favoriteRecipeList.value = repository.getFavoriteRecipes()
            _loading.value = false
        }
    }

    fun filter(query: String) {
        viewModelScope.launch {
            if (query.isNullOrBlank()) {
                _favoriteRecipeList.value = repository.getFavoriteRecipes()
            }
            _favoriteRecipeList.value = repository.filterRecipes(query)
        }
    }

    private fun filterByCreatedByUser() {
        viewModelScope.launch {
            _favoriteRecipeList.value?.filter {
                it.createdBy == "user"
            }
        }
    }

    fun onRecipeClick(id: Int) {
        _loading.value = true
        try {
            viewModelScope.launch {
                _recipeSelected.value = repository.getRecipesById(id)
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
        }
        _loading.value = false
    }


}