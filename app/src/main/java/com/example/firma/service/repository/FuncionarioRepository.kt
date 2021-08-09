package com.example.firma.service.repository

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.service.repository.file.ArquivoTxt
import com.example.firma.service.repository.local.FirmaDataBase
import java.net.URI

class FuncionarioRepository(var context: Context) {

    private val mFirmaDataBase = FirmaDataBase.getDataBase(context).funcionarioDAO()

    suspend fun insertFuncionario(funcionario: FuncionarioModel) {
        mFirmaDataBase.insert(funcionario)
        ArquivoTxt(context).actualizeTxt(funcionario,"i")
    }

    suspend fun deleteFuncionario(funcionario: FuncionarioModel) {
        mFirmaDataBase.delete(funcionario.id)
        ArquivoTxt(context).actualizeTxt(funcionario,"d")
    }

    suspend fun updateFuncionario(funcionario: FuncionarioModel) {
        mFirmaDataBase.update(funcionario)
        ArquivoTxt(context).actualizeTxt(funcionario,"i")
    }

    suspend fun getFuncionario(id: Int): FuncionarioModel{
        return mFirmaDataBase.getFuncionario(id)
    }

    suspend fun loadFuncionarios(): List<FuncionarioModel> {
        return mFirmaDataBase.getAll()
    }

    suspend fun importFuncionarios(uri: Uri): List<FuncionarioModel>{
        ArquivoTxt(context).import(uri).forEach {
            insertFuncionario(it)
            println(it)
        }
        return mFirmaDataBase.getAll()
    }
}