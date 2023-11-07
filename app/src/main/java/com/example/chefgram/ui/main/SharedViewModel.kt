package com.example.chefgram.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.common.errorhandling.CustomErrors
import com.example.chefgram.common.errorhandling.CustomException
import com.example.chefgram.common.toFilterItem
import com.example.chefgram.data.repository.reciperepo.category.Categories
import com.example.chefgram.data.repository.reciperepo.category.Categories.DairyFree
import com.example.chefgram.data.repository.reciperepo.category.Categories.GlutenFree
import com.example.chefgram.data.repository.reciperepo.category.Categories.Vegan
import com.example.chefgram.data.repository.reciperepo.category.Categories.VeryHealthy
import com.example.chefgram.data.repository.reciperepo.recipe.RecipeRepository
import com.example.chefgram.data.repository.reciperepo.recipe.local.prefs.PreferencesRepository
import com.example.chefgram.domain.model.Recipe
import com.example.chefgram.ui.main.adapters.FilterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val userPrefsRepository: PreferencesRepository
) :
    ViewModel() {

    private val _filterSelectedList = MutableLiveData<List<FilterItem>>(mutableListOf())
    val filterSelectedList: LiveData<List<FilterItem>> get() = _filterSelectedList

    private val _filterList = MutableLiveData<List<FilterItem>>()
    val filterList: LiveData<List<FilterItem>> get() = _filterList
    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

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
            fetchFilterCategories()
        }
        _filterSelectedList.value = _filterList.value
        fetchMeals()
    }

    private fun fetchFilterCategories() {
        _filterList.value = Categories.values().map { it.toFilterItem() }
    }

    private fun fetchMeals() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _recipeList.value = recipeRepository.fetchRecipes()
            } catch (e: CustomException) {
                _recipeList.value = emptyList()
                _mainError.value = e.mapToCustomError()
            } catch (e: Exception) {
                _mainError.value = CustomErrors.GeneralError
                _recipeList.value = emptyList()
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
                _mainError.value = CustomErrors.GeneralError
            }


        }
    }

    fun filter(query: String?) {
        try {
            viewModelScope.launch {
                if (query!!.isBlank()) {
                    onFilterChanged()
                } else {
                    _recipeList.value = recipeRepository.filterRecipes(query)?.toList()
                }
            }
        } catch (e: CustomException) {
            _mainError.value = e.mapToCustomError()
        } catch (e: Exception) {
            _mainError.value = CustomErrors.GeneralError
        }
    }


    fun favoriteInit() {
        viewModelScope.launch {
            _loading.value = true
            _recipeList.value = recipeRepository.getFavoriteRecipes()
            _refreshing.value = false
            _loading.value = false
        }
    }


    fun onCategoryClick(filterItem: FilterItem) {
        onFilterChanged()
    }

    private suspend fun getOriginalList() {
        try {
            _recipeList.value = recipeRepository.fetchRecipes()
        } catch (e: CustomException) {
            _mainError.value = e.mapToCustomError()
        } catch (e: Exception) {
            _mainError.value = CustomErrors.GeneralError
        }
    }

    private fun onFilterChanged() {
        _loading.value = true

        viewModelScope.launch {
            if (!checkIfFilterIsSelected()) {
                fetchMeals()
            } else {
                getOriginalList()
                _filterList.value?.forEach { filterItem ->
                    Log.d("TAG", "onFilterChanged: ${filterItem.category}")
                    if (filterItem.checked) {
                        _recipeList.value = _recipeList.value?.filter { recipe ->
                            filterByCategories(recipe, filterItem.category, filterItem.checked)
                        }
                    }
                }
                Log.d("TAG", "onFilterChanged: ${_recipeList.value}")

            }

        }
        _loading.value = false

    }


    private fun checkIfFilterIsSelected(): Boolean {
        var isAnyFilterSelected = false
        _filterList.value?.forEach { filterItem ->
            if (filterItem.checked) {
                isAnyFilterSelected = true
            }
        }
        return isAnyFilterSelected
    }

    private fun filterByCategories(
        recipe: Recipe,
        category: Categories,
        isChecked: Boolean
    ): Boolean {
        return when (category) {
            Vegan -> recipe.vegan == isChecked
            GlutenFree -> recipe.glutenFree == isChecked
            DairyFree -> recipe.dairyFree == isChecked
            VeryHealthy -> recipe.veryHealthy == isChecked
        }
    }

    fun homeInit() {
        try {
            fetchMeals()
        } catch (e: Exception) {
            _mainError.value = CustomErrors.GeneralError
        }
    }

    fun loadMoreRecipes() {
        _refreshing.value = true
        viewModelScope.launch {
            try {
                recipeRepository.loadMoreRecipes()
                fetchMeals()
            }catch (e : CustomException){
                _mainError.value = e.mapToCustomError()
            }
            catch (e: Exception){
                _mainError.value = CustomErrors.GeneralError
            }
        }
        _refreshing.value = false

    }

    fun notificationPermissionGranted(enabled: Boolean) {
        viewModelScope.launch {
            userPrefsRepository.saveNotificationsPref(enabled)
        }
    }

}

