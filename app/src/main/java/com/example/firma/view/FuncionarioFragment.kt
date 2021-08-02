package com.example.firma.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.firma.databinding.FragmentFuncionarioBinding
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.viewmodel.FuncionarioViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FuncionarioFragment : Fragment() {

    private lateinit var mFuncionarioViewModel: FuncionarioViewModel

    private val args: FuncionarioFragmentArgs by navArgs()

    private var _binding: FragmentFuncionarioBinding? = null
    private val binding: FragmentFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFuncionarioViewModel = ViewModelProvider(this).get(FuncionarioViewModel::class.java)

        _binding = FragmentFuncionarioBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener {
            handleSave()
        }

        observe()

        loadFuncionario()
        return binding.root
    }


    private fun handleSave() {
        val id: String = binding.editId.editText.toString()

        if (id == "") {
            Toast.makeText(context, "O Id não pode ser nulo", Toast.LENGTH_SHORT).show()
        } else {
            val funcionario = FuncionarioModel(
                id = binding.editId.editText.toString().toInt(),
                nome = binding.editNome.editText.toString()
            )

            lifecycleScope.launch {
                //mFuncionarioViewModel.save(funcionario)

                if (mFuncionarioViewModel.save(funcionario) == 1) {
                    mFuncionarioViewModel.update(funcionario)
                    println("f" + mFuncionarioViewModel.salvo)
                    Toast.makeText(context, "Upado", Toast.LENGTH_SHORT).show()
                } else {
                    mFuncionarioViewModel.insert(funcionario)
                    println("f" + mFuncionarioViewModel.salvo)
                    Toast.makeText(context, "Salvo", Toast.LENGTH_SHORT).show()
                }
            }
            binding.editId.editText?.text?.clear()
            binding.editNome.editText?.text?.clear()

        }

        println("fragment" + mFuncionarioViewModel.salvo)

    }

    private fun observe() {

        mFuncionarioViewModel.funcionario.observe(viewLifecycleOwner,{
            binding.apply {
                editId.editText?.setText(it.id.toString())
                editNome.editText?.setText(it.nome)
            }
        })
    }

    private fun loadFuncionario(){
        lifecycleScope.launch {
            args.funcionarioId?.let {
                mFuncionarioViewModel.load(it.toInt())
            }
        }
    }
}
