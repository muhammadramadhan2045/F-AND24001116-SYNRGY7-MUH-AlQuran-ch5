package com.example.mychallenge3.data.source

import com.example.mychallenge3.domain.model.Doa
import com.example.mychallenge3.domain.model.DoaItem

interface DoaRemoteDataSource {
    suspend fun getDoa() : Doa
    suspend fun getRandomDoa() : DoaItem
}