package com.example.mychallenge3.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.data.model.UserModel
import com.example.mychallenge3.data.repository.UserRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow


class MainViewModel(private val repository: UserRepository) : ViewModel() {

    fun getSession(): LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}