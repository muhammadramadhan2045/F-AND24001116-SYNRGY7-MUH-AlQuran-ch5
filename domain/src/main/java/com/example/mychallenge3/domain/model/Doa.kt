package com.example.mychallenge3.domain.model

data class Doa(
    val data: List<DoaItem>
)

data class DoaItem(
    val ayat: String,
    val doa: String,
    val artinya: String,
    val id: String,
    val latin: String
)