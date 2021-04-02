package com.am.recipeapp.di

import com.am.recipeapp.api.RecipesApi
import com.am.recipeapp.repository.RecipesRepository
import com.am.recipeapp.repository.RecipesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRecipeRepository(
        api: RecipesApi
    ): RecipesRepository {
        return RecipesRepositoryImpl(api)
    }
    single { provideRecipeRepository(get()) }
}

