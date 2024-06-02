package com.example.mychallenge3.domain.usecase

import com.example.mychallenge3.domain.model.DetailSurat
import com.example.mychallenge3.domain.model.Surat

interface SuratUseCase {
    suspend fun getSurat() : List<Surat>

    suspend fun getSuratById(suratId: Int): DetailSurat

    suspend fun insertSurat(surat: Surat)

    suspend fun getSuraLocaltById(suratId: Int): Surat?

    suspend fun deleteSurat(surat: Surat)

    suspend fun getAllSuratLocal(): List<Surat>
}