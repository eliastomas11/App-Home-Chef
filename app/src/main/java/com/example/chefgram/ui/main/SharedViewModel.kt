package com.example.chefgram.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.common.errorhandling.CustomErrors
import com.example.chefgram.common.errorhandling.CustomException
import com.example.chefgram.data.repository.reciperepo.RecipeRepository
import com.example.chefgram.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private val _mealsList = MutableLiveData<List<Recipe>>()
    val mealsList: LiveData<List<Recipe>> get() = _mealsList

    private val _mainError = MutableLiveData<CustomErrors>()
    val mainError: LiveData<CustomErrors> get() = _mainError

    private val _recipeSelected = MutableLiveData<Recipe>()
    val recipeSelected: LiveData<Recipe> get() = _recipeSelected

    private val _isSaved = MutableLiveData<Boolean>(false)
    val isSaved: LiveData<Boolean> get() = _isSaved

    private val _refreshing = MutableLiveData<Boolean>(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    init {
        viewModelScope.launch {
            recipeRepository.clearCache()
        }
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            try {
                _mealsList.value = recipeRepository.fetchRecipes()
            } catch (e: CustomException) {
                _mealsList.value = emptyList()
                _mainError.value = e.mapToCustomError()
            } catch (e: Exception) {
                _mainError.value = CustomErrors.GeneralError(e.message)
            }
            _loading.value = false
        }
    }

    fun onRecipeClick(id: Int) {
        _loading.value = true
        try {
            viewModelScope.launch {
                if (_recipeSelected.value?.id != id) {
                    _recipeSelected.value = recipeRepository.getRecipesById(id)
                }
                _isSaved.value = false
            }
        } catch (e: CustomException) {
            _mainError.value = e.mapToCustomError()
        }
        _loading.value = false
    }

    fun saveRecipe() {
        viewModelScope.launch {
            try {
                if (_recipeSelected.value?.isSaved == false) {
                    val saved = recipeRepository.saveRecipes(_recipeSelected.value!!)
                    _recipeSelected.value = _recipeSelected.value!!.copy(isSaved = saved > 0)
                    _isSaved.value = true
                } else {
                    _isSaved.value = false
                }

            } catch (e: CustomException) {
                _mainError.value = e.mapToCustomError()
            } catch (e: Exception) {
                _mainError.value = CustomErrors.GeneralError(e.localizedMessage)
            }


        }
    }

    fun filter(query: String?) {
        try {
            viewModelScope.launch {
                if (query!!.isBlank()) {
                    fetchMeals()
                } else {
                    _mealsList.value = recipeRepository.filterRecipes(query)?.toList()
                }
            }
        } catch (e: CustomException) {
            _mainError.value = e.mapToCustomError()
        } catch (e: Exception) {
            _mainError.value = CustomErrors.GeneralError(e.message)
        }
    }


    fun favoriteInit() {
        viewModelScope.launch {
            _mealsList.value = recipeRepository.getFavoriteRecipes()
            _loading.value = false
            _refreshing.value = false
        }

    }


    private fun filterExclusive(filterIngredients: List<String>) {
        val newRecipeList = mutableSetOf<Recipe>()

        _mealsList.value!!.forEach { recipe ->
            if (filterIngredients.containsAll(recipe.ingredients.map { it.name })) {
                newRecipeList.add(recipe)
            }
        }

        _mealsList.value = newRecipeList.toList()
    }

    private fun filterInclusive(filterIngredients: Map<Int, String>) {
        val newRecipeList = mutableSetOf<Recipe>()

        _mealsList.value!!.forEach { recipe ->
            recipe.ingredients.forEach {
                if (filterIngredients.values.contains(it.name)) {
                    newRecipeList.add(recipe)
                }
            }
        }

        _mealsList.value = newRecipeList.toList()

    }

    fun homeInit() {
        fetchMeals()
        _refreshing.value = false
    }
}
