package com.example.mychallenge3.data.repository

import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.data.pref.UserPreference
import com.example.mychallenge3.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class   UserRepository(private val userPreference: UserPreference) : IUserRepository {

    override suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    override fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    override suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}