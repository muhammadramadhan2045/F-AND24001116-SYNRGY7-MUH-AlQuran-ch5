package com.example.mychallenge3.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.data.model.Surat
import com.example.mychallenge3.data.repository.SuratRepository
import com.example.mychallenge3.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SuratRepository, private val userRepository: UserRepository) : ViewModel() {

    private val _surat: MutableLiveData<List<Surat>> = MutableLiveData()
    val surat: LiveData<List<Surat>>
        get() = _surat

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading



    init {
        getAllSurat()

    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }
    fun getAllSurat() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _surat.value = repository.getSurat()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                throwable.printStackTrace()
            }
        }
    }
}