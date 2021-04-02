package com.am.recipeapp.repository

import com.am.recipeapp.model.RecipesResponse
import com.am.recipeapp.utils.AppResult

interface RecipesRepository {
    suspend fun getAllRecipes(
        key: String,
        number: String,
        tags: String,
        limitLicence: String
    ): AppResult<RecipesResponse>

}