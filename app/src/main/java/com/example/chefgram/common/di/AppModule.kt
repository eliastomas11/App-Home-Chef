package com.example.chefgram.common.di

import androidx.room.Room
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.data.repository.MealsRepositoryImpl
import com.example.chefgram.data.repository.local.LocalDataSource
import com.example.chefgram.data.repository.local.LocalSource
import com.example.chefgram.data.repository.local.db.DatabaseLocal
import com.example.chefgram.data.repository.local.db.MealCacheDao
import com.example.chefgram.data.repository.local.db.MealDao
import com.example.chefgram.data.repository.remote.MealServiceApi
import com.example.chefgram.data.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMealService(): MealServiceApi {
        val url = "https://tasty.p.rapidapi.com/"
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MealServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(application: MyApplication): DatabaseLocal {
        val DATABASE_NAME = "meals.db"
        return Room.databaseBuilder(
            application,
            DatabaseLocal::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMealDao(db: DatabaseLocal): MealDao {
        return db.mealDao()
    }

    @Provides
    @Singleton
    fun provideMealCacheDao(db: DatabaseLocal): MealCacheDao {
        return db.mealCacheDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(mealDao: MealDao,mealCacheDao: MealCacheDao): LocalSource {
        return LocalDataSource(mealDao,mealCacheDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(mealServiceApi: MealServiceApi) : RemoteDataSource{
        return RemoteDataSource(mealServiceApi)
    }

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
    @Provides
    @Singleton
    fun provideMealRepository(coroutineDispatcher: CoroutineDispatcher,localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource) : MealsRepository{
        return MealsRepositoryImpl(coroutineDispatcher,localDataSource,remoteDataSource)
    }


}