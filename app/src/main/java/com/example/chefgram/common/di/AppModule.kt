package com.example.chefgram.common.di


import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.chefgram.R
import com.example.chefgram.data.repository.notifications.RecipeNotificationService
import com.example.chefgram.data.repository.notifications.RecipeNotificationServiceImpl
import com.example.chefgram.data.repository.reciperepo.recipe.RecipeRepository
import com.example.chefgram.data.repository.reciperepo.recipe.RecipeRepositoryImpl
import com.example.chefgram.data.repository.reciperepo.recipe.local.LocalDataSource
import com.example.chefgram.data.repository.reciperepo.recipe.local.LocalSource
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.DatabaseLocal
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.ingredientcache.IngredientCacheDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipecache.RecipeCacheDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient.IngredientDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipe.RecipeDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.prefs.PreferencesRepository
import com.example.chefgram.data.repository.reciperepo.recipe.local.prefs.PreferencesRepositoryImpl
import com.example.chefgram.data.repository.reciperepo.recipe.local.prefs.PreferencesSource
import com.example.chefgram.data.repository.reciperepo.recipe.local.prefs.PreferencesSourceImpl
import com.example.chefgram.data.repository.reciperepo.recipe.remote.RecipeInterceptor
import com.example.chefgram.data.repository.reciperepo.recipe.remote.RecipeServiceApi
import com.example.chefgram.data.repository.reciperepo.recipe.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInterceptor(@ApplicationContext context: Context): RecipeInterceptor {
        return RecipeInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideHttpClient(recipeInterceptor: RecipeInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(recipeInterceptor)
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
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
            "recipes.db")
            .createFromAsset("recipes.db")
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
    fun provideRecipeCacheDao(db: DatabaseLocal): RecipeCacheDao {
        return db.recipeCacheDAO()
    }

    @Provides
    @Singleton
    fun provideIngredientDao(db: DatabaseLocal): IngredientDao {
        return db.ingredientDao()
    }

    @Provides
    @Singleton
    fun provideIngredientCacheDao(db: DatabaseLocal): IngredientCacheDao {
        return db.ingredientCacheDao()
    }


    @Provides
    @Singleton
    fun provideLocalDataSource(
        recipeDao: RecipeDao,
        recipeCacheDAO: RecipeCacheDao,
        ingredientCacheDao: IngredientCacheDao,
        ingredientDao: IngredientDao,
    ): LocalSource {
        return LocalDataSource(recipeDao, recipeCacheDAO, ingredientCacheDao, ingredientDao)
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
    ): RecipeRepository {
        return RecipeRepositoryImpl(coroutineDispatcher, localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.file_pref_key), Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferencesSource(@ApplicationContext context: Context,sharedPreferences: SharedPreferences): PreferencesSource {
        return PreferencesSourceImpl(sharedPreferences, context)
    }
    @Provides
    @Singleton
    fun providePreferenceRepository(preferencesSource: PreferencesSource): PreferencesRepository {
        return PreferencesRepositoryImpl(preferencesSource)
    }

    @Provides
    @Singleton
    fun provideRecipeNotificationService(@ApplicationContext context: Context): RecipeNotificationService {
        return RecipeNotificationServiceImpl(context)
    }
}