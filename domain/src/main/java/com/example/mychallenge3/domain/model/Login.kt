package com.example.mychallenge3.domain.model

data class Login(
    val name: String?,
    val userId: String?,
    val token: String?,
    val error: Boolean,
    val message: String
)


