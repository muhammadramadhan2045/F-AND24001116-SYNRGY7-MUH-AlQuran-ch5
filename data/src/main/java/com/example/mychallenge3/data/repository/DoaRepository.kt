package com.example.mychallenge3.data.repository

import com.example.mychallenge3.data.source.DoaRemoteDataSource
import com.example.mychallenge3.domain.model.Doa
import com.example.mychallenge3.domain.model.DoaItem
import com.example.mychallenge3.domain.repository.IDoaRepository

class DoaRepository(private val doaRemoteDataSource: DoaRemoteDataSource) : IDoaRepository {
    override suspend fun getDoa(): Doa {
        return doaRemoteDataSource.getDoa()
    }

    override suspend fun getRandomDoa(): DoaItem {
        return doaRemoteDataSource.getRandomDoa()
    }
}