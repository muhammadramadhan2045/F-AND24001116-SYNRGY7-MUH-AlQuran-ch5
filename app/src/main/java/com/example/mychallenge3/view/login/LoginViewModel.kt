package com.example.mychallenge3.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.data.repository.UserRepository
import com.example.mychallenge3.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userUseCase.saveSession(user)
        }
    }

}