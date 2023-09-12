package com.soar.architecture.network


import com.soar.architecture.bean.RecipesItem
import retrofit2.Response
import retrofit2.http.GET

/**
 * YONG_
 */
interface API {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>
}