package com.example.firma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllFuncionariosViewModel(application: Application) : AndroidViewModel(application) {
    private val mFuncionarioRepository = FuncionarioRepository(application)

    private val mFuncionarioList = MutableLiveData<List<FuncionarioModel>>()
    val funcionarioList: LiveData<List<FuncionarioModel>> = mFuncionarioList

    suspend fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            mFuncionarioList.postValue(mFuncionarioRepository.loadFuncionarios())
        }
    }
}