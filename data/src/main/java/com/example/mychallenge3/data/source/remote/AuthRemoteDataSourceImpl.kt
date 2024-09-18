package com.example.mychallenge3.data.source.remote

import android.util.Log
import com.example.mychallenge3.data.source.AuthRemoteDataSource
import com.example.mychallenge3.data.source.remote.network.AuthService
import com.example.mychallenge3.data.utils.DataMapper
import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.model.Register
import com.google.gson.Gson
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
                name = "Admin",
                token = "admin_token",
                userId = "3242323"
            )
        } else {
         var message : String? = null
            try {
                val response = auhtService.login(email, password)
                message = response.message
                return if (response.error==false && message == "success") {
                    DataMapper.mapLoginResponseToDomain(response)
                } else {
                    Log.d("AuthRemoteDataSourceImpl", "Login failed: $message")
                    return Login(
                        error = true,
                        message = message ?: "Failed to login",
                        name = null,
                        token = null,
                        userId = null
                    )
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val errorBody = e.response()?.errorBody()?.string()
                        message = Gson().fromJson(errorBody, Login::class.java).message
                        Log.d("AuthRemoteDataSourceImpl", "Login failed: $errorBody")
                    }
                    else -> {
                        Log.d("AuthRemoteDataSourceImplement", "Login failed: ${e.localizedMessage}")
                    }
                }
                return Login(
                    error = true,
                    message = message ?: "Failed to login",
                    name = null,
                    token = null,
                    userId = null
                )
            }
        }
    }


    override suspend fun register(name: String, email: String, password: String): Register {
        var message: String? = null
        try {
            val response = auhtService.register(name, email, password)
            if (response.error == false && response.message == "User created")
                return DataMapper.mapRegisterToDomain(response)
            else {
                Log.e("AuthRemoteDataSourceImpl", "Register failed: ${response.message}")
                return Register(
                    error = true,
                    message = response.message ?: "Failed to register"
                )
            }

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorBody = e.response()?.errorBody()?.string()
                    message = Gson().fromJson(errorBody, Register::class.java).message
                    Log.d("AuthRemoteDataSourceImpl", "Register failed: $errorBody")
                }
                else -> {
                    Log.d("AuthRemoteDataSourceImplement", "Register failed: ${e.localizedMessage}")
                }
            }
            return Register(
                error = true,
                message = message ?: "Failed to register"
            )
        }

    }
}