package com.soar.architecture.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soar.architecture.base.BaseViewModel
import com.soar.architecture.bean.LoginRequest
import com.soar.architecture.bean.LoginResponse
import com.soar.architecture.bean.Recipes
import com.soar.architecture.constants.CHECK_YOUR_FIELDS
import com.soar.architecture.constants.PASS_WORD_ERROR
import com.soar.architecture.constants.USER_NAME_ERROR
import com.soar.architecture.data.LoginRepository
import com.soar.architecture.data.RecipesListRepository
import com.soar.architecture.data.Resource
import com.soar.architecture.utils.RegexUtils
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

/**
 * NAME：YONG_
 * Created at: 2023/9/5 14
 * Describe:
 */
class RecipesListViewModel : BaseViewModel(){

    lateinit var recipesListRepository : RecipesListRepository

    val recipesLiveData = MutableLiveData<Resource<Recipes>>()


    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveData.value = Resource.Loading()
            recipesListRepository.requestRecipes().collect {
                recipesLiveData.value = it
            }
        }
    }
}