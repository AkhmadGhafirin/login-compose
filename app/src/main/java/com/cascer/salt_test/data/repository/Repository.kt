package com.cascer.salt_test.data.repository

import com.cascer.salt_test.data.model.Login
import com.cascer.salt_test.data.request.LoginRequest
import com.cascer.salt_test.data.response.Result

interface Repository {
    suspend fun login(body: LoginRequest): Result<Login>
}