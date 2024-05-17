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

object Injection {
    fun provideRepository(context: Context): SuratRepository {
        val database = SuratDatabase.getInstance(context)
        val remoteDataSource = SuratRemoteDataSourceImpl(ApiConfig.provideApiService())
        val localDataSource = SuratLocalDataSourceImpl(database.suratDao())
        return SuratRepository(remoteDataSource, localDataSource)
    }


    fun provideUserRepository(context: Context): UserRepository {
        val userPreference = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(userPreference)
    }
}