package com.example.firma.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URI

class FuncionarioViewModel(application: Application) : AndroidViewModel(application) {

    private val mFuncionarioRepository = FuncionarioRepository(application)

    private val mFuncionario = MutableLiveData<FuncionarioModel>()
    var funcionario: LiveData<FuncionarioModel> = mFuncionario

    var salvo = 0

    suspend fun load(id: Int) {
        viewModelScope.launch {
            mFuncionario.postValue(mFuncionarioRepository.getFuncionario(id))
        }
    }

    fun save(funcionario: FuncionarioModel): Int {
        viewModelScope.launch {
            for (i in mFuncionarioRepository.loadFuncionarios().toList()) {
                if (i.id == funcionario.id) {
                    salvo += 1
                    println(salvo)
                    break
                } else {
                    salvo += 0
                    println(salvo)
                }
            }
            println("R $salvo")
        }
        return salvo
    }

    suspend fun insert(funcionario: FuncionarioModel) {
        viewModelScope.launch(Dispatchers.IO) {
            mFuncionarioRepository.insertFuncionario(funcionario)
        }
    }

    suspend fun update(funcionario: FuncionarioModel) {
        viewModelScope.launch(Dispatchers.IO) {
            mFuncionarioRepository.updateFuncionario(funcionario)
        }
    }

    suspend fun delete(funcionario: FuncionarioModel){
        viewModelScope.launch(Dispatchers.IO) {
            mFuncionarioRepository.deleteFuncionario(funcionario)
        }
    }
}