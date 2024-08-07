package com.example.mychallenge3.data.source

import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.model.Register

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): Login?
    suspend fun register(name: String, email: String, password: String): Register
}