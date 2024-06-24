package com.example.mychallenge3.domain.usecase

import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun saveSession(user: UserModel)
    fun getSession(): Flow<UserModel>
    suspend fun logout()
    suspend fun login(email: String, password: String):Login?
    suspend fun register(name: String, email: String, password: String) :String
}