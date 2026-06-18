package com.emanuelvillanuevagarcia.minidiarioescolarroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Nota

@Dao
interface NotaDao {

    @Insert
    suspend fun insertarNota(nota: Nota): Long

    @Query("SELECT * FROM notas WHERE usuario = :usuario ORDER BY id DESC")
    suspend fun obtenerNotasPorUsuario(usuario: String): List<Nota>

    @Query("DELETE FROM notas WHERE id = :id")
    suspend fun eliminarNota(id: Int): Int
}