package com.example.mychallenge3.data.model

import android.os.Parcelable
import com.example.mychallenge3.data.source.remote.response.AyatItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailSurat(
    val jumlahAyat: Int,
    val nama: String,
    val tempatTurun: String,
    val ayat: List<AyatItem>,
    val arti: String,
    val deskripsi: String,
    val nomor: Int,
    val namaLatin: String
): Parcelable



