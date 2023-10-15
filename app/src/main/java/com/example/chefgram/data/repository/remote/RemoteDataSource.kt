package com.example.chefgram.data.repository.remote

import android.util.Log
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val recipeService: RecipeServiceApi) :
    RemoteSource {

    override suspend fun getRecipes(): List<RecipeDto> {
        //Por que tira null?
        var lista = recipeService.getRecipes().body()?.recipes?.map { it.toRecipeDto() }
            ?: emptyList()
        return lista
    }

}
