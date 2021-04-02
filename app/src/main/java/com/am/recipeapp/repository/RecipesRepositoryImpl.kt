package com.am.recipeapp.repository

import com.am.recipeapp.api.RecipesApi
import com.am.recipeapp.model.RecipesResponse
import com.am.recipeapp.utils.AppResult
import com.am.recipeapp.utils.Helper.handleApiError
import com.am.recipeapp.utils.Helper.handleSuccess

class RecipesRepositoryImpl(private val api: RecipesApi) : RecipesRepository {
    override suspend fun getAllRecipes(
        key: String,
        number: String,
        tags: String,
        limitLicence: String
    ): AppResult<RecipesResponse> {
        return try {
            val response = api.getAllRecipes(key, number, tags, limitLicence)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }
}