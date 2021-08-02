package com.example.firma.service.repository

import android.content.Context
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.service.repository.local.FirmaDataBase

class FuncionarioRepository(context: Context) {

    private val mFirmaDataBase = FirmaDataBase.getDataBase(context).funcionarioDAO()

    suspend fun insertFuncionario(funcionario: FuncionarioModel) {
        mFirmaDataBase.insert(funcionario)
    }

    suspend fun deleteFuncionario(id: Int) {
        mFirmaDataBase.delete(id)
    }

    suspend fun updateFuncionario(funcionario: FuncionarioModel) {
        mFirmaDataBase.update(funcionario)
    }

    suspend fun getFuncionario(id: Int): FuncionarioModel{
        return mFirmaDataBase.getFuncionario(id)
    }

    suspend fun loadFuncionarios(): List<FuncionarioModel> {
        return mFirmaDataBase.getAll()
    }
}