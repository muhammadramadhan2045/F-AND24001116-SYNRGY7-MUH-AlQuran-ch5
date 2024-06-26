package com.example.mychallenge3.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.Surat
import com.example.mychallenge3.data.repository.SuratRepository
import com.example.mychallenge3.data.repository.UserRepository
import com.example.mychallenge3.domain.usecase.SuratUseCase
import com.example.mychallenge3.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val suratUseCase: SuratUseCase, private val userUseCase: UserUseCase) : ViewModel() {

    private val _surat: MutableLiveData<List<Surat>> = MutableLiveData()
    val surat: LiveData<List<Surat>>
        get() = _surat

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading


    //message
    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message



    init {
        getAllSurat()

    }

    fun logout() {
        viewModelScope.launch {
            userUseCase.logout()
        }
    }
    fun getAllSurat() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _surat.value = suratUseCase.getSurat()
                _message.value = "Success"
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                throwable.printStackTrace()
                _message.value = "Failed"
            }
        }
    }
}