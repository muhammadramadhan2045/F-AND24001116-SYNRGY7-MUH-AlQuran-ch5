package com.example.mychallenge3.domain.model

data class Login(
    val loginResult: LoginResult?,
    val error: Boolean,
    val message: String
)


data class LoginResult(
    val name: String,
    val userId: String,
    val token: String
)

