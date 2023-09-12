package com.soar.architecture.data.local

import android.content.Context
import com.soar.architecture.bean.LoginRequest
import com.soar.architecture.bean.LoginResponse
import com.soar.architecture.constants.PASS_WORD_ERROR
import com.soar.architecture.data.Resource

class LocalData (val context: Context) {

    fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        if (loginRequest == LoginRequest("ahmed@ahmed.ahmed", "ahmed")) {
            return Resource.Success(LoginResponse("123", "Ahmed", "Mahmoud",
                    "FrunkfurterAlle", "77", "12000", "Berlin",
                    "Germany", "ahmed@ahmed.ahmed"))
        }
        return Resource.DataError(PASS_WORD_ERROR)
    }
}

