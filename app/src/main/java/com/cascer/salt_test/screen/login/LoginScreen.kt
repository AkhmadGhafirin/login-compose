package com.cascer.salt_test.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cascer.salt_test.ui.theme.SALTTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text(text = "Input email here") },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = password.value,
                onValueChange = { password.value = it },
                placeholder = { Text(text = "Input password here") },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    viewModel.login(email.value.text, password.value.text)
                }) {
                Text("Login")
            }
            if (viewModel.isLoading.value) CircularProgressIndicator()
            else {
                if (viewModel.error.value.isNotEmpty()) {
                    Toast.makeText(LocalContext.current, viewModel.error.value, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(LocalContext.current, "Berhasil Login = ${viewModel.loginToken.value}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    SALTTestTheme {
        LoginScreen()
    }
}