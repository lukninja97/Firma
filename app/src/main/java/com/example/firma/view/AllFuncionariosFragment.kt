package com.example.firma.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.DocumentsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firma.databinding.FragmentAllFuncionariosBinding
import com.example.firma.view.adapter.FuncionarioAdapter
import com.example.firma.viewmodel.AllFuncionariosViewModel
import java.io.File

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

        binding.fabAdd.setOnClickListener {
            val action =
                AllFuncionariosFragmentDirections.actionNavAllFuncionariosToFuncionarioFragment(null)
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.fabAddtxt.setOnClickListener {
            val file = File(context?.filesDir, "")
            println(file)
            openFile(file.toUri())
        }

        mAdapter =
            FuncionarioAdapter(mAllFuncionarioViewModel.funcionarioList.value) { funcionarioModel ->
                val action =
                    AllFuncionariosFragmentDirections.actionNavAllFuncionariosToFuncionarioFragment(
                        funcionarioModel
                    )
                Navigation.findNavController(binding.root).navigate(action)
            }

        val recycler = binding.recyclerAllFuncionarios
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mAllFuncionarioViewModel.load()

        observer()

        return binding.root
    }

    private fun observer() {
        mAllFuncionarioViewModel.funcionarioList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateFuncionarios(it)

            binding.textVazio.isVisible = it.isEmpty()
        })
    }

    private fun openFile(pickerInitialUri: Uri) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            //addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            //putExtra(Intent.EXTRA_TITLE, "funcionarios.txt")

            //putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)

        }
        startActivityForResult(intent, 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK) {
            data?.data?.also { uri ->
                mAllFuncionarioViewModel.import(uri)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}