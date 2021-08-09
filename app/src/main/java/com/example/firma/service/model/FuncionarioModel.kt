package com.example.firma.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "funcionario")
data class FuncionarioModel (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    var nome: String = "",
    var complemento: String = "",
    var reservado1: Int = 0,
    var reservado2: Int = 0
        ): Serializable