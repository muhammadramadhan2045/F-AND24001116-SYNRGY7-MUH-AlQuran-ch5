package com.example.mychallenge3.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mychallenge3.data.di.Injection
import com.example.mychallenge3.data.repository.SuratRepository
import com.example.mychallenge3.data.repository.UserRepository
import com.example.mychallenge3.view.detail.DetailViewModel
import com.example.mychallenge3.view.favorite.FavoriteViewModel
import com.example.mychallenge3.view.home.HomeViewModel
import com.example.mychallenge3.view.login.LoginViewModel
import com.example.mychallenge3.view.main.MainViewModel

class ViewModelFactory(private val repository: SuratRepository,private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository,userRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java)->{
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java)->{
                MainViewModel(userRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }


    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory{
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context),Injection.provideUserRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}