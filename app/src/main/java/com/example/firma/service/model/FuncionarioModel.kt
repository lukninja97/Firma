package com.example.firma.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "funcionario")
data class FuncionarioModel (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val nome: String = ""
        ): Serializable