package com.example.chefgram.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.domain.model.Recipe
import com.example.chefgram.domain.model.RecipeIngredient
import com.example.chefgram.ui.home.FilterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val mealsRepository: MealsRepository) :
    ViewModel() {

    private val _ingredientList = MutableLiveData<List<FilterItem>>()
    val ingredientList: LiveData<List<FilterItem>> get() = _ingredientList
    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private val _mealsList = MutableLiveData<List<Recipe>>()
    val mealsList: LiveData<List<Recipe>> get() = _mealsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _recipeSelected = MutableLiveData<Recipe>()
    val recipeSelected: LiveData<Recipe> get() = _recipeSelected

    private val _isSaved = MutableLiveData<Boolean>(false)
    val isSaved: LiveData<Boolean> get() = _isSaved

    init {
        fetchMeals()
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            try {
                _mealsList.value = mealsRepository.fetchMeals()
            } catch (e: Exception) {
                _mealsList.value = emptyList()
                _errorMessage.value = e.message
            }
            _loading.value = false
        }
    }

    fun onMealClick(id: Int) {
        _loading.value = true
        try {
            viewModelScope.launch {
                _recipeSelected.value = mealsRepository.getMealById(id)
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
        }
        _loading.value = false
    }

    fun saveMeal() {
        viewModelScope.launch {
            try {
                if (_recipeSelected.value?.isSaved == false) {
                    val saved = mealsRepository.saveMeal(_recipeSelected.value)
                    _isSaved.value = saved > 0
                    _recipeSelected.value = _recipeSelected.value!!.copy(isSaved = _isSaved.value!!)
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }

        }
    }

    private fun filterExclsuive(filterIngredients: List<RecipeIngredient>) {
        val exclusiveList = mutableSetOf<Recipe>()
        _mealsList.value!!.filterTo(exclusiveList) {
            it.ingredients.containsAll(filterIngredients)
        }
        _mealsList.value = exclusiveList.toList()
    }

    private fun filterInclusive(filterIngredients: List<RecipeIngredient>) {
        val abarcativeList = mutableSetOf<Recipe>()
        for (ingredient in filterIngredients) {
            _mealsList.value!!.filterTo(abarcativeList) {
                it.ingredients.contains(ingredient)
            }
        }
        _mealsList.value = abarcativeList.toList()
    }

    fun filterByIngredients(filterParams: FilterParams) {
        if (filterParams.exclusive) {
            filterExclsuive(filterParams.ingredient)
        } else {
            filterInclusive(filterParams.ingredient)

        }


        //version 2
        /*for(ingredient in filterParams.ingredient){
            for(recipe in _mealsList.value!!){
                for(recipeIngredient in recipe.ingredients){
                    if(recipeIngredient.name == ingredient.name){
                        newList.add(recipe)
                    }
                }
            }
        }*/
    }

    fun onFilterClick() {

    }


}

/*@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val mealRepository: MealsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mealRepository) as T
    }
}*/