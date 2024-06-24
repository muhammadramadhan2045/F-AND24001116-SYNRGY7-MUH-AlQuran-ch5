package com.example.mychallenge3.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.data.repository.UserRepository
import com.example.mychallenge3.domain.model.Login
import com.example.mychallenge3.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _loginResult = MutableLiveData<Result<Login>>()
    val loginResult: LiveData<Result<Login>> = _loginResult

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
                _loginResult.value = Result.success(login!!)
            } catch (e: Exception) {
                _loading.value = false
                _loginResult.value = Result.failure(e)
            }
        }
    }





}