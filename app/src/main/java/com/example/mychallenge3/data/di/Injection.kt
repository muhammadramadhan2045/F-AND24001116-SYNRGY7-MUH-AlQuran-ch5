package com.example.mychallenge3.data.di

import android.content.Context
import com.example.mychallenge3.data.pref.UserPreference
import com.example.mychallenge3.data.pref.dataStore
import com.example.mychallenge3.data.repository.SuratRepository
import com.example.mychallenge3.data.repository.UserRepository
import com.example.mychallenge3.data.source.local.SuratLocalDataSourceImpl
import com.example.mychallenge3.data.source.local.room.SuratDatabase
import com.example.mychallenge3.data.source.remote.SuratRemoteDataSourceImpl
import com.example.mychallenge3.data.source.remote.network.ApiConfig
import com.example.mychallenge3.domain.repository.ISuratRepository
import com.example.mychallenge3.domain.repository.IUserRepository
import com.example.mychallenge3.domain.usecase.SuratInteractor
import com.example.mychallenge3.domain.usecase.SuratUseCase
import com.example.mychallenge3.domain.usecase.UserInteractor
import com.example.mychallenge3.domain.usecase.UserUseCase

object Injection {
    fun provideRepository(context: Context): ISuratRepository {
        val database = SuratDatabase.getInstance(context)
        val remoteDataSource = SuratRemoteDataSourceImpl(ApiConfig.provideApiService())
        val localDataSource = SuratLocalDataSourceImpl(database.suratDao())
        return SuratRepository(remoteDataSource, localDataSource)
    }


    fun provideUserRepository(context: Context): IUserRepository {
        val userPreference = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(userPreference)
    }

    fun provideUserUseCase(context: Context):UserUseCase{
        val userRepository = provideUserRepository(context)
        return UserInteractor(userRepository)
    }


    fun provideSuratUseCase(context: Context):SuratUseCase{
        val suratRepository = provideRepository(context)
        return SuratInteractor(suratRepository)
    }
}