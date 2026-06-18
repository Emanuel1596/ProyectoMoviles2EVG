package com.emanuelvillanuevagarcia.minidiarioescolarroom.repositories

import com.emanuelvillanuevagarcia.minidiarioescolarroom.dao.UsuarioDao
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun registrarUsuario(usuario: String, contrasena: String): Boolean {
        val existente = usuarioDao.obtenerUsuarioPorNombre(usuario)

        if (existente != null) {
            return false
        }

        val resultado = usuarioDao.insertarUsuario(
            Usuario(
                usuario = usuario,
                contrasena = contrasena,
                ultimaConexion = ""
            )
        )

        return resultado != -1L
    }

    suspend fun validarUsuario(usuario: String, contrasena: String): Boolean {
        val usuarioEncontrado = usuarioDao.validarUsuario(usuario, contrasena)
        return usuarioEncontrado != null
    }

    suspend fun obtenerUltimaConexion(usuario: String): String {
        val ultima = usuarioDao.obtenerUltimaConexion(usuario)

        return if (ultima.isNullOrEmpty()) {
            "Primera vez que inicia sesión"
        } else {
            ultima
        }
    }

    suspend fun actualizarUltimaConexion(usuario: String, fecha: String): Boolean {
        val filas = usuarioDao.actualizarUltimaConexion(usuario, fecha)
        return filas > 0
    }
}