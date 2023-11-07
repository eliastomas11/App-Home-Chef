package com.example.chefgram.ui.createrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.data.repository.reciperepo.recipe.RecipeRepository
import com.example.chefgram.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(private val repository: RecipeRepository) :
    ViewModel() {

    private val _errorEmptyFields = MutableLiveData<Boolean>(false)
    val errorEmptyFields: LiveData<Boolean> get() = _errorEmptyFields
    fun saveRecipe(recipeComponentes: List<String>) {
        val userRecipe = createRecipe(recipeComponentes)

        if (userRecipe != null) {
            viewModelScope.launch {
                repository.createRecipe(userRecipe.toRecipeDto())
            }
        }else{
            //Setear boolean para set error de los text input fields que tiene que observar
            _errorEmptyFields.value = true
        }
    }

    fun validate(textToValidate: String): Boolean {
        return textToValidate.isNullOrBlank().not()
    }

    private fun createRecipe(recipeComponentes: List<String>): Recipe? {
        recipeComponentes.forEach {
            if (!validate(it)) {
                return null
            }
        }
        //crear la receta con todos los elementos
        return null
    }


}