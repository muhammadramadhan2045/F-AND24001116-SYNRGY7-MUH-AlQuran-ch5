package com.example.mychallenge3.domain.usecase

import com.example.mychallenge3.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun saveSession(user: UserModel)
    fun getSession(): Flow<UserModel>
    suspend fun logout()
}