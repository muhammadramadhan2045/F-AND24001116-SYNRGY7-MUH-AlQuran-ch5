package com.example.mychallenge3.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mychallenge3.data.source.local.entity.SuratEntity

@Dao
interface SuratDao {
    @Query("SELECT * FROM surat")
    suspend fun getSurat(): List<SuratEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurat(surat: SuratEntity)
    @Delete
    suspend fun deleteSurat(surat: SuratEntity)

    @Query("SELECT * FROM surat WHERE nomor = :suratId")
    suspend fun getSuratById(suratId: Int): SuratEntity
}