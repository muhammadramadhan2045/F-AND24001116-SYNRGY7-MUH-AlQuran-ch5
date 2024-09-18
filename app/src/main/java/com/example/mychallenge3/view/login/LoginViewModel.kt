package com.example.mychallenge3.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _loginResult = MutableLiveData<Login>()
    val loginResult: LiveData<Login> = _loginResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userUseCase.saveSession(user)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val login = userUseCase.login(email, password)
                _loading.value = false
                _loginResult.value = login!!
            } catch (e: Exception) {
                _loading.value = false
                _loginResult.value = Login(
                    error = true,
                    message = e.message ?: "Failed to login",
                    name = null,
                    token = null,
                    userId = null
                )
            }
        }
    }





}