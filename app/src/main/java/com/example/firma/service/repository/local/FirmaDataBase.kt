package com.example.firma.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firma.service.model.FuncionarioModel

@Database(entities = [FuncionarioModel::class], version = 1)
abstract class FirmaDataBase : RoomDatabase() {

    abstract fun funcionarioDAO(): FuncionarioDAO

    companion object{
        @Volatile
        private lateinit var INSTANCE : FirmaDataBase

        fun getDataBase(context: Context): FirmaDataBase{
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, FirmaDataBase::class.java, "firma_database")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}