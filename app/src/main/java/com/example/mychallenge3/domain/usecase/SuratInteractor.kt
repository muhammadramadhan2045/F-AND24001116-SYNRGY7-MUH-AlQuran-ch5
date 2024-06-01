package com.example.mychallenge3.domain.usecase

import com.example.mychallenge3.domain.model.DetailSurat
import com.example.mychallenge3.domain.model.Surat
import com.example.mychallenge3.domain.repository.ISuratRepository

class SuratInteractor(private val suratRepository: ISuratRepository) : SuratUseCase{
    override suspend fun getSurat(): List<Surat> {
        return suratRepository.getSurat()
    }

    override suspend fun getSuratById(suratId: Int): DetailSurat {
        return suratRepository.getSuratById(suratId)
    }

    override suspend fun insertSurat(surat: Surat) {
        return suratRepository.insertSurat(surat)
    }

    override suspend fun getSuraLocaltById(suratId: Int): Surat? {
        return suratRepository.getSuraLocaltById(suratId)
    }

    override suspend fun deleteSurat(surat: Surat) {
        return suratRepository.deleteSurat(surat)
    }

    override suspend fun getAllSuratLocal(): List<Surat> {
        return suratRepository.getAllSuratLocal()
    }
}