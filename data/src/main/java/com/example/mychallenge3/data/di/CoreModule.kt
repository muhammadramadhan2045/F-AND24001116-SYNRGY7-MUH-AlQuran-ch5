package com.example.mychallenge3.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mychallenge3.data.pref.UserPreference
import com.example.mychallenge3.data.repository.SuratRepository
import com.example.mychallenge3.data.repository.UserRepository
import com.example.mychallenge3.data.source.AuthRemoteDataSource
import com.example.mychallenge3.data.source.SuratLocalDataSource
import com.example.mychallenge3.data.source.SuratRemoteDataSource
import com.example.mychallenge3.data.source.local.SuratLocalDataSourceImpl
import com.example.mychallenge3.data.source.local.room.SuratDatabase
import com.example.mychallenge3.data.source.remote.AuthRemoteDataSourceImpl
import com.example.mychallenge3.data.source.remote.SuratRemoteDataSourceImpl
import com.example.mychallenge3.data.source.remote.network.ApiService
import com.example.mychallenge3.data.source.remote.network.AuthService
import com.example.mychallenge3.domain.repository.ISuratRepository
import com.example.mychallenge3.domain.repository.IUserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<SuratDatabase>().suratDao() }
    single {
        Room.databaseBuilder(
            get(),
            SuratDatabase::class.java,
            "surat.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(ChuckerInterceptor(androidContext()))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://equran.id/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
    single {
        val authRetrofit = Retrofit.Builder()
            .baseUrl("https://story-api.dicoding.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        authRetrofit.create(AuthService::class.java)
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
val prefModule = module {
    single { androidContext().dataStore }
    single { UserPreference(get()) }
}

val repositoryModule = module {
    single<SuratLocalDataSource> { SuratLocalDataSourceImpl(get()) }
    single<SuratRemoteDataSource> { SuratRemoteDataSourceImpl(get()) }
    single<AuthRemoteDataSource> {AuthRemoteDataSourceImpl(get())  }
    single<ISuratRepository> { SuratRepository(get(), get()) }
    single<IUserRepository> { UserRepository(get(),get()) }
}