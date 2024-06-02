package com.example.mychallenge3.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surat")
data class SuratEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "nomor")
    val nomor: Int,

    @ColumnInfo(name = "jumlahAyat")
    val jumlahAyat: Int,

    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "tempatTurun")
    val tempatTurun: String,

    @ColumnInfo(name = "arti")
    val arti: String,

    @ColumnInfo(name = "deskripsi")
    val deskripsi: String,

    @ColumnInfo(name = "namaLatin")
    val namaLatin: String
)
