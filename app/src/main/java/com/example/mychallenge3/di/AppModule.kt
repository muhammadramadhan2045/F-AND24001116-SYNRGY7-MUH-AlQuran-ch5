package com.example.mychallenge3.di

import com.example.mychallenge3.domain.usecase.SuratInteractor
import com.example.mychallenge3.domain.usecase.SuratUseCase
import com.example.mychallenge3.domain.usecase.UserInteractor
import com.example.mychallenge3.domain.usecase.UserUseCase
import com.example.mychallenge3.view.detail.DetailViewModel
import com.example.mychallenge3.view.favorite.FavoriteViewModel
import com.example.mychallenge3.view.home.HomeViewModel
import com.example.mychallenge3.view.login.LoginViewModel
import com.example.mychallenge3.view.main.MainViewModel
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter.Companion.factory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<SuratUseCase> { SuratInteractor(get()) }
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module{
    viewModel{HomeViewModel(get(),get())}
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}