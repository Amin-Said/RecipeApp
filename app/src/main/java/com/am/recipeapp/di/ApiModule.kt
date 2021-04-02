package com.am.recipeapp.di

import com.am.recipeapp.api.RecipesApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideRecipesApi(retrofit: Retrofit): RecipesApi {
        return retrofit.create(RecipesApi::class.java)
    }
    single { provideRecipesApi(get()) }

}