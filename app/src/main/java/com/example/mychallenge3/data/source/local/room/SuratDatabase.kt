package com.example.mychallenge3.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mychallenge3.data.source.local.entity.SuratEntity

@Database(entities = [SuratEntity::class], version = 1)
abstract class SuratDatabase :RoomDatabase(){
    abstract fun suratDao(): SuratDao

    companion object {
        private var INSTANCE: SuratDatabase? = null

        fun getInstance(context: Context): SuratDatabase {
            if (INSTANCE == null) {
                synchronized(SuratDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SuratDatabase::class.java,
                        "surat.db"
                    ).build()
                }
            }
            return INSTANCE as SuratDatabase
        }
    }
}