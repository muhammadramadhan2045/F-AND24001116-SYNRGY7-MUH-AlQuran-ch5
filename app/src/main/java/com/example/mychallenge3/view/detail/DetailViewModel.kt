package com.example.mychallenge3.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.data.model.DetailSurat
import com.example.mychallenge3.data.model.Surat
import com.example.mychallenge3.data.repository.SuratRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: SuratRepository) : ViewModel() {

    private val _surat: MutableLiveData<DetailSurat> = MutableLiveData()
    val surat: LiveData<DetailSurat>
        get() = _surat

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    fun getSuratById(id: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _surat.value = repository.getSuratById(id)
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                throwable.printStackTrace()
            }
        }
    }


    private val _insertFavoriteSurat: MutableLiveData<Boolean> = MutableLiveData()
    val insertFavoriteSurat: LiveData<Boolean>
        get() = _insertFavoriteSurat

    fun saveToFavoriteSurat(surat: Surat) {
        viewModelScope.launch {
            try {
                repository.insertSurat(surat)
                _insertFavoriteSurat.value = true
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

    private val _deleteFavoriteSurat: MutableLiveData<Boolean> = MutableLiveData()
    val deleteFavoriteSurat: LiveData<Boolean>
        get() = _deleteFavoriteSurat
    fun deleteFromFavoriteSurat(surat: Surat) {
        viewModelScope.launch {
            try {
                repository.deleteSurat(surat)
                _deleteFavoriteSurat.value = true
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }


    private val _suratFavorite: MutableLiveData<Surat> = MutableLiveData()
    val suratFavorite: LiveData<Surat>
        get() = _suratFavorite

    fun getSuratFromFavorite(id: Int) {
        viewModelScope.launch {
            try {
                _suratFavorite.value = repository.getSuraLocaltById(
                    id
                )
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }





}