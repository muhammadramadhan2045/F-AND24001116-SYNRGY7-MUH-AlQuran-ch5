package com.example.mychallenge3.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailSurat(
    val jumlahAyat: Int,
    val nama: String,
    val tempatTurun: String,
    val ayat: List<Ayat>,
    val arti: String,
    val deskripsi: String,
    val nomor: Int,
    val namaLatin: String
): Parcelable



