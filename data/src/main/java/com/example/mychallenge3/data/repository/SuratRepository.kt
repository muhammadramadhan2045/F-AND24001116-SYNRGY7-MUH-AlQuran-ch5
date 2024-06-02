package com.example.mychallenge3.data.repository

import com.example.mychallenge3.domain.model.DetailSurat
import com.example.mychallenge3.domain.model.Surat
import com.example.mychallenge3.data.source.SuratLocalDataSource
import com.example.mychallenge3.data.source.SuratRemoteDataSource
import com.example.mychallenge3.domain.repository.ISuratRepository
import com.example.mychallenge3.data.utils.DataMapper

class SuratRepository(
    private val suratRemoteDataSource: SuratRemoteDataSource,
    private val suratLocalDataSource: SuratLocalDataSource
) : ISuratRepository {


    companion object{
        @Volatile
        private var instance: SuratRepository? = null
        fun getInstance(
            suratRemoteDataSource: SuratRemoteDataSource,
            suratLocalDataSource: SuratLocalDataSource
        ): SuratRepository =
            instance ?: synchronized(this) {
                instance ?: SuratRepository(suratRemoteDataSource, suratLocalDataSource)
            }
    }
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

    override suspend fun getSuraLocaltById(suratId: Int): Surat? {
        return suratLocalDataSource.getSuratById(suratId)?.let {
            DataMapper.mapSuratEntityToDomain(it)
        }
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