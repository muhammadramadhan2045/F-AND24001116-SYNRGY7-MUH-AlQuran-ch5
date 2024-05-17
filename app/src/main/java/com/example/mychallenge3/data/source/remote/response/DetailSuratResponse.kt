package com.example.mychallenge3.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DetailSuratResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
data class AyatItem(

	@field:SerializedName("teksArab")
	val teksArab: String,

	@field:SerializedName("teksLatin")
	val teksLatin: String,

	@field:SerializedName("nomorAyat")
	val nomorAyat: Int,

	@field:SerializedName("teksIndonesia")
	val teksIndonesia: String
):Parcelable

data class Data(

	@field:SerializedName("jumlahAyat")
	val jumlahAyat: Int,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("tempatTurun")
	val tempatTurun: String,

	@field:SerializedName("ayat")
	val ayat: List<AyatItem>,

	@field:SerializedName("arti")
	val arti: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("nomor")
	val nomor: Int,

	@field:SerializedName("namaLatin")
	val namaLatin: String
)


