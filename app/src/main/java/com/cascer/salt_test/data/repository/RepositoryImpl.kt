package com.cascer.salt_test.data.repository

import com.cascer.salt_test.data.api.ApiService
import com.cascer.salt_test.data.model.Login
import com.cascer.salt_test.data.model.mapper.Mapper.toModel
import com.cascer.salt_test.data.request.LoginRequest
import com.cascer.salt_test.data.response.Result
import com.cascer.salt_test.util.ExceptionUtil.toException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
): Repository{
    override suspend fun login(body: LoginRequest): Result<Login> {
        return try {
            val request = apiService.login(body)
            if (request.isSuccessful) {
                Result.Success(request.body()?.toModel() ?: Login(""))
            } else {
                Result.Error(request.errorBody().toException())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}