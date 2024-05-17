package com.example.mychallenge3.domain


import com.example.mychallenge3.data.model.DetailSurat
import com.example.mychallenge3.data.model.Surat

interface ISuratRepository {
    suspend fun getSurat() : List<Surat>

    suspend fun getSuratById(suratId: Int): DetailSurat

    suspend fun insertSurat(surat: Surat)

    suspend fun getSuraLocaltById(suratId: Int): Surat?

    suspend fun deleteSurat(surat: Surat)

    suspend fun getAllSuratLocal(): List<Surat>

}