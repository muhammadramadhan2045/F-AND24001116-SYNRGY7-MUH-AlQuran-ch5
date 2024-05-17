package com.example.mychallenge3.data.source.remote

import com.example.mychallenge3.data.model.DetailSurat
import com.example.mychallenge3.data.model.Surat
import com.example.mychallenge3.data.source.SuratRemoteDataSource
import com.example.mychallenge3.data.source.remote.network.ApiService
import com.example.mychallenge3.utils.DataMapper

class SuratRemoteDataSourceImpl(
    private val apiService: ApiService
) :SuratRemoteDataSource{
    override suspend fun getSurat(): List<Surat> {
        return apiService.getSurat().data.let {
            DataMapper.mapSuratResponseToEntities(it)
        }
    }

    override suspend fun getSuratById(suratId: Int): DetailSurat {
        return apiService.getSuratById(suratId).data.let {
            DataMapper.mapDetailSuratResponseToEntity(it)
        }
    }


}