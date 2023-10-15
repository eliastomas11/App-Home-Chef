package com.example.chefgram.common.di

import android.content.Context
import androidx.room.Room
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.data.repository.MealsRepositoryImpl
import com.example.chefgram.data.repository.local.LocalDataSource
import com.example.chefgram.data.repository.local.LocalSource
import com.example.chefgram.data.repository.local.db.DatabaseLocal
import com.example.chefgram.data.repository.local.db.filteringredient.FilterIngredientDao
import com.example.chefgram.data.repository.local.db.cache.recipecache.RecipeCacheDAO
import com.example.chefgram.data.repository.local.db.recipe.RecipeDao
import com.example.chefgram.data.repository.local.db.recipewithingredient.RecipeWithIngredientDao
import com.example.chefgram.data.repository.remote.RecipeServiceApi
import com.example.chefgram.data.repository.remote.RecipeInterceptor
import com.example.chefgram.data.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(RecipeInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        val url = "https://api.spoonacular.com/"
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMealService(retrofit: Retrofit): RecipeServiceApi {
        return retrofit.create(RecipeServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): DatabaseLocal {
        return Room.databaseBuilder(
            context,
            DatabaseLocal::class.java,
            "meals.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(db: DatabaseLocal): RecipeDao {
        return db.recipeDao()
    }

    @Provides
    @Singleton
    fun provideRecipeCacheDao(db: DatabaseLocal): RecipeCacheDAO {
        return db.recipeCacheDAO()
    }

    @Provides
    @Singleton
    fun provideRecipeWithIngredientDao(db: DatabaseLocal): RecipeWithIngredientDao {
        return db.recipeWithIngredientDao()
    }

    @Provides
    @Singleton
    fun provideFilterIngredientDao(db: DatabaseLocal): FilterIngredientDao {
        return db.filterIngredientDao()
    }


    @Provides
    @Singleton
    fun provideLocalDataSource(recipeDao: RecipeDao, recipeCacheDAO: RecipeCacheDAO): LocalSource {
        return LocalDataSource(recipeDao, recipeCacheDAO)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(recipeServiceApi: RecipeServiceApi): RemoteDataSource {
        return RemoteDataSource(recipeServiceApi)
    }

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideMealRepository(
        coroutineDispatcher: CoroutineDispatcher,
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MealsRepository {
        return MealsRepositoryImpl(coroutineDispatcher, localDataSource, remoteDataSource)
    }


}