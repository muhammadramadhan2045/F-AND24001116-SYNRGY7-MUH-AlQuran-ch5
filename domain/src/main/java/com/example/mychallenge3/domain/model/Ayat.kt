package com.example.mychallenge3.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ayat(
    val teksArab: String,
    val teksLatin: String,
    val nomorAyat: Int,
    val teksIndonesia: String
): Parcelable


