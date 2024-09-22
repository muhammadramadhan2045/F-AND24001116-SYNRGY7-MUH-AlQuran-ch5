package com.example.mychallenge3.data.repository

import com.example.mychallenge3.data.di.repositoryModule
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.data.pref.UserPreference
import com.example.mychallenge3.data.source.AuthRemoteDataSource
import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.model.Register
import com.example.mychallenge3.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userPreference: UserPreference,private val authRemoteDataSource: AuthRemoteDataSource) : IUserRepository {


    override suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    override fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    override suspend fun logout() { 
        userPreference.logout()
    }

    override suspend fun login(email: String, password: String) :Login?{
        return authRemoteDataSource.login(email, password)
    }

    override suspend fun register(name: String, email: String, password: String): Register {
        return authRemoteDataSource.register(name, email, password)
    }


}