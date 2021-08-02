package com.example.firma.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firma.databinding.FragmentAllFuncionariosBinding
import com.example.firma.view.adapter.FuncionarioAdapter
import com.example.firma.viewmodel.AllFuncionariosViewModel
import kotlinx.coroutines.launch

class AllFuncionariosFragment : Fragment() {

    private lateinit var mAllFuncionarioViewModel: AllFuncionariosViewModel
    private lateinit var mAdapter: FuncionarioAdapter

    private var _binding: FragmentAllFuncionariosBinding? = null
    private val binding: FragmentAllFuncionariosBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAllFuncionarioViewModel = ViewModelProvider(this).get(AllFuncionariosViewModel::class.java)

        _binding = FragmentAllFuncionariosBinding.inflate(inflater, container, false)

        binding.fabAdd.setOnClickListener{
            val action = AllFuncionariosFragmentDirections.actionNavAllFuncionariosToFuncionarioFragment(null)
            Navigation.findNavController(binding.root).navigate(action)
        }

        mAdapter = FuncionarioAdapter(mAllFuncionarioViewModel.funcionarioList.value){ funcionarioModel ->
            val funcionarioId = funcionarioModel.id.toString()
            val action = AllFuncionariosFragmentDirections.actionNavAllFuncionariosToFuncionarioFragment(funcionarioId)
            Navigation.findNavController(binding.root).navigate(action)
        }

        val recycler = binding.recyclerAllFuncionarios
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        lifecycleScope.launch{
            mAllFuncionarioViewModel.load()
        }

        observer()

        return binding.root
    }

    private fun observer(){
        mAllFuncionarioViewModel.funcionarioList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateFuncionarios(it)
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}