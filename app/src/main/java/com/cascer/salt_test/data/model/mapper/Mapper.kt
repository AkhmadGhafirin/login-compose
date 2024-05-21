package com.cascer.salt_test.data.model.mapper

import com.cascer.salt_test.data.model.Login
import com.cascer.salt_test.data.response.LoginResponse

object Mapper {
    fun LoginResponse.toModel() = Login(
        token = token.orEmpty()
    )
}