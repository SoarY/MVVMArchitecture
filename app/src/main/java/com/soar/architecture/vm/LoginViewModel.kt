package com.soar.architecture.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soar.architecture.base.BaseViewModel
import com.soar.architecture.bean.LoginRequest
import com.soar.architecture.bean.LoginResponse
import com.soar.architecture.constants.CHECK_YOUR_FIELDS
import com.soar.architecture.constants.PASS_WORD_ERROR
import com.soar.architecture.constants.USER_NAME_ERROR
import com.soar.architecture.data.LoginRepository
import com.soar.architecture.data.Resource
import com.soar.architecture.utils.RegexUtils
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

/**
 * NAME：YONG_
 * Created at: 2023/9/5 14
 * Describe:
 */
class LoginViewModel : BaseViewModel(){

    lateinit var loginRepository : LoginRepository

    val loginLiveData = MutableLiveData<Resource<LoginResponse>>()

    fun doLogin(userName: String, passWord: String) {
        val isUsernameValid = RegexUtils.isValidEmail(userName)
        val isPassWordValid = passWord.trim().length > 4
        if (isUsernameValid && !isPassWordValid) {
            loginLiveData.value = Resource.DataError(PASS_WORD_ERROR)
        } else if (!isUsernameValid && isPassWordValid) {
            loginLiveData.value = Resource.DataError(USER_NAME_ERROR)
        } else if (!isUsernameValid && !isPassWordValid) {
            loginLiveData.value = Resource.DataError(CHECK_YOUR_FIELDS)
        } else {
            viewModelScope.launch {
                loginLiveData.value = Resource.Loading()
                loginRepository.doLogin(loginRequest = LoginRequest(userName, passWord)).collect {
                    loginLiveData.value = it
                }
            }
        }
    }
}