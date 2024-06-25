package com.example.mychallenge3.data.source.remote

import com.example.mychallenge3.data.source.remote.network.AuthService
import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.model.LoginResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class AuthRemoteDataSourceImplTest {

    private lateinit var authRemoteDataSource: AuthRemoteDataSourceImpl
    private val authService: AuthService = mock()

    @Before
    fun setUp() {
        authRemoteDataSource = AuthRemoteDataSourceImpl(authService)
    }


    @Test
    fun login_success() = runTest {
        val email = "admin"
        val password = "admin"
        val expectedResponse = Login(
            error = false,
            message = "success",
            loginResult = LoginResult("Admin", "3242323","admin_token" )
        )
        // Execute
        val actualResponse = authRemoteDataSource.login(email, password)

        assertEquals(expectedResponse, actualResponse)
    }
}