package com.example.mychallenge3.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.data.repository.UserRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import com.example.mychallenge3.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.Flow


class MainViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getSession(): LiveData<UserModel>{
        return userUseCase.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            userUseCase.logout()
        }
    }
}