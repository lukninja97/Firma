package com.example.firma.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firma.databinding.RowFuncionarioBinding
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.view.viewholder.FuncionarioViewHolder

class FuncionarioAdapter(private var funcionarios: List<FuncionarioModel>?, val clickListener: (FuncionarioModel) -> Unit): RecyclerView.Adapter<FuncionarioViewHolder>() {

    private var mFuncionarioList: List<FuncionarioModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioViewHolder {
        val binding = RowFuncionarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FuncionarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        holder.apply {
            bind(mFuncionarioList[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        return mFuncionarioList.count()
    }

    fun updateFuncionarios(list: List<FuncionarioModel>){
        mFuncionarioList = list
        notifyDataSetChanged()
    }
}