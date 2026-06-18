package com.emanuelvillanuevagarcia.minidiarioescolarroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertarUsuario(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios WHERE usuario = :usuario LIMIT 1")
    suspend fun obtenerUsuarioPorNombre(usuario: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE usuario = :usuario AND contrasena = :contrasena LIMIT 1")
    suspend fun validarUsuario(usuario: String, contrasena: String): Usuario?

    @Query("SELECT ultimaConexion FROM usuarios WHERE usuario = :usuario LIMIT 1")
    suspend fun obtenerUltimaConexion(usuario: String): String?

    @Query("UPDATE usuarios SET ultimaConexion = :fecha WHERE usuario = :usuario")
    suspend fun actualizarUltimaConexion(usuario: String, fecha: String): Int
}