package com.example.mychallenge3.domain.model

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)