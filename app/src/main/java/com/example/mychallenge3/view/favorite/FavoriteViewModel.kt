package com.example.mychallenge3.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.Surat
import com.example.mychallenge3.data.repository.SuratRepository
import com.example.mychallenge3.domain.usecase.SuratUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(private val suratUseCase: SuratUseCase) : ViewModel() {
    private val _surat = MutableLiveData<List<Surat>>()
    val surat: LiveData<List<Surat>> = _surat

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    fun getSuratFromLocal() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _surat.value = suratUseCase.getAllSuratLocal()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable
            }
        }
    }

    init {
        getSuratFromLocal()
    }
}