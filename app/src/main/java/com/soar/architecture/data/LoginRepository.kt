package com.soar.architecture.data

import com.soar.architecture.bean.LoginRequest
import com.soar.architecture.bean.LoginResponse
import com.soar.architecture.data.local.LocalData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * NAME：YONG_
 * Created at: 2023/9/6 10
 * Describe:
 */
class LoginRepository {

    lateinit var localRepository : LocalData

    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            emit(localRepository.doLogin(loginRequest))
        }.flowOn(Dispatchers.IO)
    }
}