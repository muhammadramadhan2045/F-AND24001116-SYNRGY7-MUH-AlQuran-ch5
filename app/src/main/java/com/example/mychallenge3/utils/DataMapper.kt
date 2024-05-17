package com.example.mychallenge3.utils

import com.example.mychallenge3.data.model.DetailSurat
import com.example.mychallenge3.data.model.Surat
import com.example.mychallenge3.data.source.local.entity.SuratEntity
import com.example.mychallenge3.data.source.remote.response.Data
import com.example.mychallenge3.data.source.remote.response.DataItem

object DataMapper {

    fun mapSuratResponseToEntities(input: List<DataItem>): List<Surat> {
        return input.map {
            Surat(
                nomor = it.nomor,
                nama = it.nama,
                arti = it.arti,
                deskripsi = it.deskripsi,
                namaLatin = it.namaLatin,
                jumlahAyat = it.jumlahAyat,
                tempatTurun = it.tempatTurun,
            )
        }
    }

    fun mapSuratEntitiesToDomain(input: List<SuratEntity>): List<Surat> {
        return input.map {
            mapSuratEntityToDomain(it)
        }
    }


    fun mapSuratEntityToDomain(input: SuratEntity) = Surat(
        nomor = input.nomor,
        nama = input.nama,
        arti = input.arti,
        deskripsi = input.deskripsi,
        namaLatin = input.namaLatin,
        jumlahAyat = input.jumlahAyat,
        tempatTurun = input.tempatTurun,
    )

    fun mapSuratDomainToEntity(input: Surat) = SuratEntity(
        nomor = input.nomor,
        nama = input.nama,
        arti = input.arti,
        deskripsi = input.deskripsi,
        namaLatin = input.namaLatin,
        jumlahAyat = input.jumlahAyat,
        tempatTurun = input.tempatTurun,
    )

    fun mapDetailSuratResponseToEntity(input: Data): DetailSurat {
        return DetailSurat(
            nama = input.nama,
            arti = input.arti,
            ayat = input.ayat,
            namaLatin = input.namaLatin,
            nomor = input.nomor,
            jumlahAyat = input.jumlahAyat,
            deskripsi = input.deskripsi,
            tempatTurun = input.tempatTurun,
        )
    }


}