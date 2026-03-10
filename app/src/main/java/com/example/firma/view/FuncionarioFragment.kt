package com.example.firma.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.firma.R
import com.example.firma.databinding.FragmentFuncionarioBinding
import com.example.firma.service.model.FuncionarioModel
import com.example.firma.viewmodel.FuncionarioViewModel
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

        binding.buttonDelete.setOnClickListener {
            handleDelete()
        }

        observe()

        loadFuncionario()
        return binding.root
    }

    private fun handleDelete() {
        val id: String = binding.editId.editText?.text.toString()

        if (id == "") {
            Toast.makeText(context, "O Id não pode ser nulo", Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(context)
                .setTitle("Exclusão de Funcionário")
                .setMessage("Deseja excluir Funcionário?")
                .setPositiveButton("Sim") { dialog, which ->
                    lifecycleScope.launch {
                        args.funcionario?.let {
                            mFuncionarioViewModel.delete(it)
                            Toast.makeText(
                                context,
                                "Funcionário excluído com sucesso!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            Navigation.findNavController(binding.root)
                                .navigate(FuncionarioFragmentDirections.actionFuncionarioFragmentToNavAllFuncionarios())
                        }
                    }
                }
                .setNeutralButton("Cancelar", null)
                .show()
        }
    }


    private fun handleSave() {
        val id: String = binding.editId.editText?.text.toString()
        val reservado1 = binding.editReservado1.editText?.text.toString()
        val reservado2 = binding.editReservado2.editText?.text.toString()

        if (id == "") {
            Toast.makeText(context, "O Id não pode ser nulo", Toast.LENGTH_SHORT).show()
        } else if (reservado1 == "" || reservado2 == "") {
            Toast.makeText(context, "O Reservado não pode ser nulo", Toast.LENGTH_SHORT).show()
        } else {
            val funcionario = FuncionarioModel(
                id = id.toInt(),
                nome = binding.editNome.editText?.text.toString().uppercase(),
                complemento = if (binding.radioOperador.isChecked) {
                    "Operador"
                } else {
                    "Mecanico"
                },
                reservado1 = reservado1.toInt(),
                reservado2 = reservado2.toInt()
            )

            lifecycleScope.launch {
                if (args.funcionario?.id == null) {
                    mFuncionarioViewModel.insert(funcionario)
                    Toast.makeText(context, "Funcionario salvo com sucesso", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    mFuncionarioViewModel.update(funcionario)
                    Toast.makeText(
                        context,
                        "Funcionario atualizado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                findNavController().navigate(FuncionarioFragmentDirections.actionFuncionarioFragmentToNavAllFuncionarios())
            }

            binding.editId.editText?.text?.clear()
            binding.editNome.editText?.text?.clear()
            binding.editReservado1.editText?.text?.clear()
            binding.editReservado2.editText?.text?.clear()

        }
    }

    private fun observe() {

        binding.radioOperador.setOnClickListener {
            binding.imageComplemento.setImageResource(R.drawable.operador)
        }
        binding.radioMecanico.setOnClickListener {
            binding.imageComplemento.setImageResource(R.drawable.mecanico)
        }

        mFuncionarioViewModel.funcionario.observe(viewLifecycleOwner, {
            binding.apply {
                editId.editText?.setText(it.id.toString())
                editNome.editText?.setText(it.nome)
                if (it.complemento == "OPERADOR") {
                    radioOperador.isChecked = true
                    imageComplemento.setImageResource(R.drawable.operador)
                } else {
                    radioMecanico.isChecked = true
                    imageComplemento.setImageResource(R.drawable.mecanico)
                }
                editReservado1.editText?.setText(it.reservado1.toString())
                editReservado2.editText?.setText(it.reservado2.toString())
            }
        })
    }

    private fun loadFuncionario() {
        lifecycleScope.launch {
            args.funcionario?.let {
                mFuncionarioViewModel.load(it.id)
            }
        }

        binding.buttonDelete.isVisible = (args.funcionario?.id != null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            editId.editText?.isCursorVisible = false
            editNome.editText?.isCursorVisible = false
            editReservado1.editText?.isCursorVisible = false
            editReservado2.editText?.isCursorVisible = false
        }
    }
}
