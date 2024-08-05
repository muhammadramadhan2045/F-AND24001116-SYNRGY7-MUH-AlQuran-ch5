package com.example.mychallenge3.view.doa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.domain.model.Doa
import com.example.mychallenge3.domain.usecase.DoaUseCase
import kotlinx.coroutines.launch

class DoaViewModel(private val doaUseCase: DoaUseCase): ViewModel(){

    private val _doa : MutableLiveData<Doa> = MutableLiveData()
    val doa : MutableLiveData<Doa>
        get() = _doa

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    val loading : MutableLiveData<Boolean>
        get() = _loading

    private val _message : MutableLiveData<String> = MutableLiveData()
    val message : MutableLiveData<String>
        get() = _message

    init {
        getDoa()
    }


    fun getDoa(){
        viewModelScope.launch {
            try {
                _loading.value = true
                _doa.value = doaUseCase.getDoa()
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