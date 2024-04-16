package com.micudasoftware.data.recipes.di

import com.micudasoftware.data.recipes.api.RecipesApi
import com.micudasoftware.data.recipes.datasource.RecipesDataSource
import com.micudasoftware.data.recipes.repository.RecipesRepositoryImpl
import com.micudasoftware.domain.recipes.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RecipesDataModule {

    @Provides
    fun provideRecipesApi(retrofit: Retrofit): RecipesApi =
        retrofit.create(RecipesApi::class.java)

    @Provides
    fun provideRecipesDataSource(recipesApi: RecipesApi) =
        RecipesDataSource(recipesApi)

    @Provides
    fun provideRecipesRepository(
        recipesDataSource: RecipesDataSource
    ): RecipesRepository = RecipesRepositoryImpl(recipesDataSource)
}