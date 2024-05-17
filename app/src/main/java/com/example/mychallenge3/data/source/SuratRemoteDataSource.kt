package com.example.mychallenge3.data.source

import com.example.mychallenge3.data.model.DetailSurat
import com.example.mychallenge3.data.model.Surat

interface   SuratRemoteDataSource {
    suspend fun getSurat() : List<Surat>
    suspend fun getSuratById(suratId: Int): DetailSurat
}