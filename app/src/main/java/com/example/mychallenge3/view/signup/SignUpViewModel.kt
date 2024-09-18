package com.example.mychallenge3.view.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.Register
import com.example.mychallenge3.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _registerResult = MutableLiveData<Register?>()
    val registerResult: LiveData<Register?> = _registerResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading



    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val register = userUseCase.register(name, email, password)
                _loading.value = false
                _registerResult.value = register
            } catch (e: Exception) {
                _loading.value = false
                _registerResult.value = Register(
                    error = true,
                    message = e.message ?: "Failed to register",
                )
            }
        }
    }


    fun shownMessage() {
        _registerResult.value = null
    }

}