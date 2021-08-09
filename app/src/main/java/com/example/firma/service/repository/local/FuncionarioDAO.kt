package com.example.firma.service.repository.local

import androidx.room.*
import com.example.firma.service.model.FuncionarioModel

@Dao
interface FuncionarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(funcionario: FuncionarioModel)

    @Update
    suspend fun update(funcionario: FuncionarioModel) : Int

    @Query("DELETE FROM funcionario WHERE id = :id")
    suspend fun delete(id: Int): Int

    @Query("SELECT * FROM funcionario WHERE id = :id")
    suspend fun getFuncionario(id: Int): FuncionarioModel

    @Query("SELECT * FROM funcionario")
    suspend fun getAll(): List<FuncionarioModel>

    @Query("SELECT * FROM funcionario WHERE complemento = 'Operador'")
    suspend fun getOperadores(): List<FuncionarioModel>

    @Query("SELECT * FROM funcionario WHERE complemento = 'Mecanico'")
    suspend fun getMecanicos(): List<FuncionarioModel>
}