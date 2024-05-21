package com.cascer.salt_test.data.api

import com.cascer.salt_test.data.request.LoginRequest
import com.cascer.salt_test.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<LoginResponse>
}