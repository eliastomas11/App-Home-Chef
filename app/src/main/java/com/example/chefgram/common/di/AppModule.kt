package com.example.chefgram.common.di

import android.content.Context
import androidx.room.Room
import com.example.chefgram.data.repository.MealsRepository
import com.example.chefgram.data.repository.MealsRepositoryImpl
import com.example.chefgram.data.repository.local.LocalDataSource
import com.example.chefgram.data.repository.local.LocalSource
import com.example.chefgram.data.repository.local.db.DatabaseLocal
import com.example.chefgram.data.repository.local.db.MealCacheDao
import com.example.chefgram.data.repository.local.db.MealDao
import com.example.chefgram.data.repository.remote.MealServiceApi
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
    fun provideMealService(retrofit: Retrofit): MealServiceApi {
        return retrofit.create(MealServiceApi::class.java)
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
    fun provideLocalDataSource(mealDao: MealDao, mealCacheDao: MealCacheDao): LocalSource {
        return LocalDataSource(mealDao, mealCacheDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(mealServiceApi: MealServiceApi): RemoteDataSource {
        return RemoteDataSource(mealServiceApi)
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