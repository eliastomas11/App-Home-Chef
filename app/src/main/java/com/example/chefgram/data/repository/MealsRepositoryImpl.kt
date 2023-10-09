package com.example.chefgram.data.repository

import com.example.chefgram.ui.model.Meal
import com.example.chefgram.common.toMeal
import com.example.chefgram.data.repository.local.LocalDataSource
import com.example.chefgram.data.repository.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.RuntimeException

class MealsRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MealsRepository {

    override suspend fun fetchMeals(): List<Meal> {
        //throw RuntimeException("Not implemented")
        return withContext(dispatcher) {
            var mealsDto = localDataSource.getMeals()
            if(mealsDto.isEmpty()){
                mealsDto = remoteDataSource.getMeals()
                localDataSource.saveMeals(mealsDto)
            }
            return@withContext mealsDto.map { it.toMeal() }
        }
    }

}
