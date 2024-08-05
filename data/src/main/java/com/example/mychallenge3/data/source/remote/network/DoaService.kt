package com.example.mychallenge3.data.source.remote.network

import com.example.mychallenge3.data.source.remote.response.DoaResponse
import com.example.mychallenge3.data.source.remote.response.DoaResponseItem
import retrofit2.http.GET

interface DoaService {

    @GET("/")
    suspend fun getDoa(): DoaResponse

    @GET("/doa/v1/random")
    suspend fun getRandomDoa() : DoaResponseItem
}