package com.am.recipeapp.utils

import com.am.recipeapp.model.Recipe
import com.am.recipeapp.model.RecipesResponse

object FakeData {
    fun generateFakeDataResponse(): RecipesResponse {
        val recipe = Recipe(
            1,
            emptyList(),
            true,
            20,
            "credit",
            emptyList(),
            true,
            emptyList(),
            emptyList(),
            emptyList(),
            "gaps",
            true,
            10.0,
            10,
            "https://spoonacular.com/recipeImages/579247-556x370.jpg",
            "jpg",
            "instructions",
            "license",
            true,
            emptyList(),
            "10",
            60,
            10.0,
            60,
            260,
            "source name",
            "source url",
            10.0,
            "url",
            "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum.",
            true,
            "Fake recipe title",
            vegan = true,
            vegetarian = true,
            veryHealthy = true,
            veryPopular = true,
            200
        )
        val recipeList = listOf(recipe, recipe, recipe,recipe,recipe)

        return RecipesResponse(recipeList)

    }
}