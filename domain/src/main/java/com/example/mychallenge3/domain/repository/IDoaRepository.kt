package com.example.mychallenge3.domain.repository

import com.example.mychallenge3.domain.model.Doa
import com.example.mychallenge3.domain.model.DoaItem

interface IDoaRepository {
    suspend fun getDoa() : Doa

    suspend fun getRandomDoa() : DoaItem
}