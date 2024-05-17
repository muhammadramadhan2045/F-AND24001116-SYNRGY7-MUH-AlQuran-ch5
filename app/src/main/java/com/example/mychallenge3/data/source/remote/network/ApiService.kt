package com.example.mychallenge3.data.source.remote.network

import com.example.mychallenge3.data.source.remote.response.DetailSuratResponse
import com.example.mychallenge3.data.source.remote.response.SuratResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("surat/")
    suspend fun getSurat(): SuratResponse

    @GET("surat/{id}")
    suspend fun getSuratById(
        @Path("id") suratId: Int
    ): DetailSuratResponse
}