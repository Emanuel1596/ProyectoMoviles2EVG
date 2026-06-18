package com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Nota
import com.emanuelvillanuevagarcia.minidiarioescolarroom.repositories.NotaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class NotaViewModel(private val repository: NotaRepository) : ViewModel() {

    private val _notas = MutableStateFlow<List<Nota>>(emptyList())
    val notas: StateFlow<List<Nota>> = _notas

    fun cargarNotas(usuario: String) {
        viewModelScope.launch {
            _notas.value = repository.obtenerNotas(usuario)
        }
    }

    fun agregarNota(
        usuario: String,
        titulo: String,
        contenido: String,
        resultado: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val guardado = repository.agregarNota(usuario, titulo, contenido, Date())
            resultado(guardado)

            if (guardado) {
                cargarNotas(usuario)
            }
        }
    }

    fun eliminarNota(id: Int, usuario: String) {
        viewModelScope.launch {
            repository.eliminarNota(id)
            cargarNotas(usuario)
        }
    }
}