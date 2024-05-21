package com.cascer.salt_test.screen.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.salt_test.data.repository.Repository
import com.cascer.salt_test.data.request.LoginRequest
import com.cascer.salt_test.data.response.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val loginToken: MutableState<String> = mutableStateOf("")
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val error: MutableState<String> = mutableStateOf("")

    fun login(email: String, password: String) = viewModelScope.launch {
        isLoading.value = true
        loginToken.value = ""
        error.value = ""
        when (val result = repository.login(LoginRequest(email, password))) {
            is Result.Success -> {
                isLoading.value = false
                loginToken.value = result.data.token
            }
            is Result.Error -> {
                isLoading.value = false
                error.value = result.exception.message.orEmpty()
            }
        }
    }
}