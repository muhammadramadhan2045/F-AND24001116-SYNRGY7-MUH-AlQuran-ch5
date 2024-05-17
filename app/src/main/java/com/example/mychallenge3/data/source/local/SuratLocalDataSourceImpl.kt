package com.example.mychallenge3.data.source.local

import com.example.mychallenge3.data.source.SuratLocalDataSource
import com.example.mychallenge3.data.source.local.entity.SuratEntity
import com.example.mychallenge3.data.source.local.room.SuratDao

class SuratLocalDataSourceImpl(private val suratDao: SuratDao) : SuratLocalDataSource {
    override suspend fun getAllSuratLocal(): List<SuratEntity> {
        return suratDao.getSurat()
    }

    override suspend fun getSuratById(suratId: Int): SuratEntity {
        return suratDao.getSuratById(suratId)
    }

    override suspend fun insertSurat(surat: SuratEntity) {
        suratDao.insertSurat(surat)
    }

    override suspend fun deleteSurat(surat: SuratEntity) {
        suratDao.deleteSurat(surat)
    }

}