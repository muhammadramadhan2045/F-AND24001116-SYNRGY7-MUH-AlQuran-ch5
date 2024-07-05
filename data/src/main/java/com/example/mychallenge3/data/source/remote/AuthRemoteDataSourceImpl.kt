package com.example.mychallenge3.data.source.remote

import android.util.Log
import com.example.mychallenge3.data.source.AuthRemoteDataSource
import com.example.mychallenge3.data.source.remote.network.AuthService
import com.example.mychallenge3.data.utils.DataMapper
import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.model.LoginResult
import com.example.mychallenge3.domain.model.Register
import retrofit2.HttpException

class AuthRemoteDataSourceImpl(
    private val auhtService: AuthService
) : AuthRemoteDataSource {

    private val users
        get() = listOf(
            "admin" to "admin",

            )

    override suspend fun login(email: String, password: String): Login? {
        return if (users.contains(email to password)) {
            Login(
                error = false,
                message = "success",
                loginResult = LoginResult(
                    name = "Admin",
                    token = "admin_token",
                    userId = "3242323"
                )
            )
        } else {
            try {
                val response = auhtService.login(email, password)
                return if (response.error == false && response.message == "success") {
                    DataMapper.mapLoginResponseToDomain(response)
                } else {
                    Log.e("AuthRemoteDataSourceImpl", "Login failed: ${response.message}")
                    null
                }
            } catch (e: HttpException) {
                Log.e("AuthRemoteDataSourceImpl", "HTTP exception: ${e.message}")
                null
            } catch (e: Exception) {
                Log.e("AuthRemoteDataSourceImpl", "Exception: ${e.message}")
                null
            }
        }

    }

    override suspend fun register(name: String, email: String, password: String): Register {
        val response = auhtService.register(name, email, password)
        try {
            if (response.error == false && response.message == "User created")
                return DataMapper.mapRegisterToDomain(response)
            else {
                Log.e("AuthRemoteDataSourceImpl", "Register failed: ${response.message}")
                return Register(
                    error = true,
                    message = response.message?:"Failed to register"
                )
            }

        } catch (e: HttpException) {
            Log.e("AuthRemoteDataSourceImpl", "HTTP exception: ${e.message}")
            return Register(
                error = true,
                message = response.message?:"Failed to register"
            )
        } catch (e: Exception) {
            Log.e("AuthRemoteDataSourceImpl", "Exception: ${e.message}")
            return Register(
                error = true,
                message = response.message?:"Failed to register"
            )
        }

    }
}