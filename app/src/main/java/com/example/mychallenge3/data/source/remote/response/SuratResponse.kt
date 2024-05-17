package com.example.mychallenge3.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SuratResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String
)


data class DataItem(

	@field:SerializedName("jumlahAyat")
	val jumlahAyat: Int,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("tempatTurun")
	val tempatTurun: String,

	@field:SerializedName("arti")
	val arti: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("nomor")
	val nomor: Int,

	@field:SerializedName("namaLatin")
	val namaLatin: String
)
