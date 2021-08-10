package com.example.firma.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.net.URI

class AllFuncionariosViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    private val mFuncionarioRepository = FuncionarioRepository(application)

    private val mFuncionarioList = MutableLiveData<List<FuncionarioModel>>()
    val funcionarioList: LiveData<List<FuncionarioModel>> = mFuncionarioList

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            mFuncionarioList.postValue(mFuncionarioRepository.loadFuncionarios())
        }
    }

    fun import(uri: Uri){
        viewModelScope.launch(Dispatchers.Default) {
            mFuncionarioList.postValue(mFuncionarioRepository.importFuncionarios(uri))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}