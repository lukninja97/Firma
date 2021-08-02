package com.example.firma.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firma.databinding.RowFuncionarioBinding
import com.example.firma.service.model.FuncionarioModel

class FuncionarioViewHolder(private val binding: RowFuncionarioBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(funcionario: FuncionarioModel, clickListener: (FuncionarioModel) -> Unit){
        binding.apply {
            textId.text = funcionario.id.toString()
            textNome.text = funcionario.nome
        }

        binding.root.setOnClickListener { clickListener(funcionario) }
    }
}