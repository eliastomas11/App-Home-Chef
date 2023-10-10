package com.example.chefgram.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.domain.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val mealsRepository: MealsRepository) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private val _mealsList = MutableLiveData<List<Meal>>()
    val mealsList: LiveData<List<Meal>> get() = _mealsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _mealSelected = MutableLiveData<Meal>()
    val mealSelected: LiveData<Meal> get() = _mealSelected

    private val _isSaved = MutableLiveData<Boolean>(false)
    val isSaved: LiveData<Boolean> get() = _isSaved

    init {
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
                _mealSelected.value = mealsRepository.getMealById(id)
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
        }
        _loading.value = false
    }

    fun saveMeal() {
        viewModelScope.launch {
            try {
                if (_mealSelected.value?.isSaved == false) {
                    val saved = mealsRepository.saveMeal(_mealSelected.value)
                    _isSaved.value = saved > 0
                    _mealSelected.value = _mealSelected.value!!.copy(isSaved = _isSaved.value!!)
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }

        }
    }

    fun filterByIngredients(filterParams: FilterParams) {
        if (filterParams.exclusive) {
            //Excluyente
            val exclusiveList = mutableSetOf<Meal>()
            _mealsList.value!!.filterTo(exclusiveList) {
                it.ingredients.containsAll(filterParams.ingredient)
            }
            _mealsList.value = exclusiveList.toList()
        } else {
            //Incluyente
            val abarcativeList = mutableSetOf<Meal>()
            for (ingredient in filterParams.ingredient) {
                _mealsList.value!!.filterTo(abarcativeList) {
                    it.ingredients.contains(ingredient)
                }
            }
            _mealsList.value = abarcativeList.toList()
        }

        fun filterByFavorites(): List<Meal> {
            viewModelScope.launch {
                return@launch _mealsList.value = mealsRepository.getFavorites()
            }
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

}

/*@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val mealRepository: MealsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mealRepository) as T
    }
}*/