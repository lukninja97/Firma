package com.example.firma.service.repository.file

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.firma.service.model.FuncionarioModel
import java.io.*
import java.net.URI
import java.util.*

class ArquivoTxt(var context: Context) {
    private val arquivo = "funcionarios.txt"
    private var file = File(context.getFileStreamPath(arquivo).toURI())
    private val funcionarios = arrayListOf<FuncionarioModel>()

    fun actualizeTxt(funcionario: FuncionarioModel, key: String) {

        /**
         * LER O ARQUIVO
         */
        try {
            if (file.exists()) {
                file.readLines().forEach { line ->
                    println(line)
                    line.trim().split(";").let {
                        val newFuncionario =
                            FuncionarioModel(
                                it[0].toInt(),
                                it[1],
                                it[2],
                                it[3].toInt(),
                                it[4].toInt()
                            )
                        funcionarios.add(newFuncionario)
                    }
                }
            } else {
                file.createNewFile()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        when (key) {
            /**
             * INSERT OU UPDATE
             */
            "i" -> {
                if (funcionarios.isEmpty()) {
                    funcionarios.add(funcionario)
                } else {
                    val func = funcionarios.find { funcionario.id == it.id }
                    func?.apply {
                        nome = funcionario.nome
                        complemento = funcionario.complemento
                        reservado1 = funcionario.reservado1
                        reservado2 = funcionario.reservado2
                    } ?: funcionarios.add(funcionario)
                }
            }

            /**
             * DELETE
             */
            "d" -> {
                val funcremove = funcionarios.find { funcionario.id == it.id }
                funcionarios.remove(funcremove)
            }
        }

        /**
         * ESCREVER NO ARQUIVO
         */
        try {
            file.writeText("")
            funcionarios.forEach {
                file.appendText("${it.id};${it.nome};${it.complemento};${it.reservado1};${it.reservado2}\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun import(uri: Uri): List<FuncionarioModel> {

        context.contentResolver.openInputStream(uri).use { inputStream ->
            BufferedReader(
                InputStreamReader(inputStream, "ISO-8859-1")
            ).use { reader ->

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    println(line)
                    line?.split(";")?.let {
                        val newFuncionario =
                            FuncionarioModel(
                                it[0].toInt(),
                                it[1].uppercase(),
                                if(it[2] == "O") "Operador" else "Mecanico",
                                it[3].toInt(),
                                it[4].toInt()
                            )
                        funcionarios.add(newFuncionario)
                    }
                }
            }
        }

        return funcionarios
    }
}