package com.soar.architecture.data

import com.soar.architecture.bean.Recipes
import com.soar.architecture.bean.RecipesItem
import com.soar.architecture.constants.NO_INTERNET_CONNECTION
import com.soar.architecture.network.RetrofitClient
import com.soar.architecture.utils.NetworkUtils
import com.soar.architecture.network.APIMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * NAME：YONG_
 * Created at: 2023/9/6 10
 * Describe:
 */
class RecipesListRepository {

    fun requestRecipes(): Flow<Resource<Recipes>> {
        return flow {
            emit(exRequestRecipes())
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun exRequestRecipes(): Resource<Recipes> {
        if (!NetworkUtils.isNetworkConnected())
            return Resource.DataError(NO_INTERNET_CONNECTION)

        val fetchRecipes = RetrofitClient.getApi(APIMain.API_HF)!!.fetchRecipes()
        if (fetchRecipes.isSuccessful)
            return Resource.Success(Recipes(fetchRecipes.body() as ArrayList<RecipesItem>))
        else
            return Resource.DataError(fetchRecipes.code())
    }
}