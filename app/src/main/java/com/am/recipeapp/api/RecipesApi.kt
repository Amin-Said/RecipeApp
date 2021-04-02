package com.am.recipeapp.api

import com.am.recipeapp.model.RecipesResponse
import com.am.recipeapp.utils.Config
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface RecipesApi {
    @GET(Config.BASE_RETURN)
    suspend fun getAllRecipes(
        @Query(Config.KEY_PARAM) key: String?,
        @Query(Config.KEY_NUMBER) number: String?,
        @Query(Config.KEY_TAGS) tags: String?,
        @Query(Config.KEY_LIMIT_LICENCE) limitLicence: String?
    ): Response<RecipesResponse>
}