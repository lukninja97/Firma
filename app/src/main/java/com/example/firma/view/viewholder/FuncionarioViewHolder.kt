package com.example.firma.view.viewholder

import android.app.AlertDialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firma.R
import com.example.firma.databinding.RowFuncionarioBinding
import com.example.firma.service.model.FuncionarioModel

class FuncionarioViewHolder(private val binding: RowFuncionarioBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(funcionario: FuncionarioModel, clickListener: (FuncionarioModel) -> Unit){
        binding.apply {
            textId.text = "#${funcionario.id}"
            textNome.text = funcionario.nome
            if (funcionario.complemento == "Operador"){
                imagePerson.setImageResource(R.drawable.operador)
            }else{
                imagePerson.setImageResource(R.drawable.mecanico)
            }
        }

        binding.root.setOnClickListener { clickListener(funcionario) }
    }

}