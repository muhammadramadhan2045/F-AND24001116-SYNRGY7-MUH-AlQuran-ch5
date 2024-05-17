package com.example.mychallenge3.data.source

import com.example.mychallenge3.data.source.local.entity.SuratEntity

interface SuratLocalDataSource {
    suspend fun getAllSuratLocal(): List<SuratEntity>
    suspend fun getSuratById(suratId: Int): SuratEntity
    suspend fun insertSurat(surat: SuratEntity)
    suspend fun deleteSurat(surat: SuratEntity)
}