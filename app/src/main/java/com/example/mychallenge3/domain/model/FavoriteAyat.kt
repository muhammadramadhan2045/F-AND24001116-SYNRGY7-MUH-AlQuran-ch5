package com.example.mychallenge3.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteAyat(
    val nomor: Int,
    val nomorAyat: String,
    val namaLatin: String,
    val teksArab: String,
    val teksIndonesia: String,
    val teksLatin: String,
    var isFavorite: Boolean
):Parcelable
