package com.example.mychallenge3.domain.usecase

import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override suspend fun saveSession(user: UserModel) {
        userRepository.saveSession(user)
    }

    override fun getSession(): Flow<UserModel> {
        return userRepository.getSession()
    }

    override suspend fun logout() {
        userRepository.logout()
    }

    override suspend fun login(email: String, password: String) :Login? {
        return userRepository.login(email, password)
    }

    override suspend fun register(name: String, email: String, password: String): String {
        return userRepository.register(name, email, password)
    }
}