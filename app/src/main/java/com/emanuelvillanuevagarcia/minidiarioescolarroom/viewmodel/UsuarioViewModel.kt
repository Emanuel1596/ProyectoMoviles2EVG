package com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuelvillanuevagarcia.minidiarioescolarroom.repositories.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    fun registrarUsuario(
        usuario: String,
        contrasena: String,
        resultado: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val guardado = repository.registrarUsuario(usuario, contrasena)
            resultado(guardado)
        }
    }

    fun validarUsuario(
        usuario: String,
        contrasena: String,
        resultado: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val valido = repository.validarUsuario(usuario, contrasena)
            resultado(valido)
        }
    }

    fun obtenerUltimaConexion(
        usuario: String,
        resultado: (String) -> Unit
    ) {
        viewModelScope.launch {
            val ultima = repository.obtenerUltimaConexion(usuario)
            resultado(ultima)
        }
    }

    fun actualizarUltimaConexion(usuario: String, fecha: String) {
        viewModelScope.launch {
            repository.actualizarUltimaConexion(usuario, fecha)
        }
    }
}