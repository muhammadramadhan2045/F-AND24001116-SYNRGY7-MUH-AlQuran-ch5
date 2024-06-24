package com.example.mychallenge3.data.source

import com.example.mychallenge3.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveSession(user: UserModel)
    fun getSession(): Flow<UserModel>
    suspend fun logout()
}