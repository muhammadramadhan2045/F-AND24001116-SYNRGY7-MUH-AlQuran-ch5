package com.example.mychallenge3

import android.app.Application
import com.example.mychallenge3.data.di.databaseModule
import com.example.mychallenge3.data.di.networkModule
import com.example.mychallenge3.data.di.prefModule
import com.example.mychallenge3.data.di.repositoryModule
import com.example.mychallenge3.di.useCaseModule
import com.example.mychallenge3.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    prefModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}