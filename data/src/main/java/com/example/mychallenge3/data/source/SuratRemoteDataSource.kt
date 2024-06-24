package com.example.mychallenge3.data.source

import com.example.mychallenge3.domain.model.DetailSurat
import com.example.mychallenge3.domain.model.Surat

interface SuratRemoteDataSource {
    suspend fun getSurat() : List<Surat>
    suspend fun getSuratById(suratId: Int): DetailSurat
}