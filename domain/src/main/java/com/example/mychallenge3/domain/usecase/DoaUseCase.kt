package com.example.mychallenge3.domain.usecase

import com.example.mychallenge3.domain.model.Doa
import com.example.mychallenge3.domain.model.DoaItem

interface DoaUseCase {
    suspend fun getDoa() : Doa

    suspend fun getRandomDoa() : DoaItem
}