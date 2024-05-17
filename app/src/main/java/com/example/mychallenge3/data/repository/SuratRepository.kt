package com.example.mychallenge3.data.repository

import com.example.mychallenge3.data.model.DetailSurat
import com.example.mychallenge3.data.model.Surat
import com.example.mychallenge3.data.source.SuratLocalDataSource
import com.example.mychallenge3.data.source.SuratRemoteDataSource
import com.example.mychallenge3.domain.ISuratRepository
import com.example.mychallenge3.utils.DataMapper

class SuratRepository(
    private val suratRemoteDataSource: SuratRemoteDataSource,
    private val suratLocalDataSource: SuratLocalDataSource
) :ISuratRepository{
    override suspend fun getSurat(): List<Surat> {
        return suratRemoteDataSource.getSurat()
    }

    override suspend fun getSuratById(suratId: Int): DetailSurat {
        return suratRemoteDataSource.getSuratById(suratId)
    }


    override suspend fun insertSurat(surat: Surat) {
        suratLocalDataSource.insertSurat(
            DataMapper.mapSuratDomainToEntity(surat)
        )
    }

    override suspend fun getSuraLocaltById(suratId: Int): Surat {
        return DataMapper.mapSuratEntityToDomain(
            suratLocalDataSource.getSuratById(suratId)
        )
    }

    override suspend fun deleteSurat(surat: Surat) {
        suratLocalDataSource.deleteSurat(
            DataMapper.mapSuratDomainToEntity(surat)
        )
    }

    override suspend fun getAllSuratLocal(): List<Surat> {
        return DataMapper.mapSuratEntitiesToDomain(
            suratLocalDataSource.getAllSuratLocal()
        )
    }


}