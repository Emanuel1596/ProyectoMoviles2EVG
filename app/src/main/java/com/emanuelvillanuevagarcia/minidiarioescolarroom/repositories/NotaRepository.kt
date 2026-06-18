package com.emanuelvillanuevagarcia.minidiarioescolarroom.repositories

import com.emanuelvillanuevagarcia.minidiarioescolarroom.dao.NotaDao
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Nota
import java.util.Date

class NotaRepository(private val notaDao: NotaDao) {

    suspend fun agregarNota(usuario: String, titulo: String, contenido: String, fecha: Date): Boolean {
        val resultado = notaDao.insertarNota(
            Nota(
                usuario = usuario,
                titulo = titulo,
                contenido = contenido,
                fecha = fecha
            )
        )

        return resultado != -1L
    }

    suspend fun obtenerNotas(usuario: String): List<Nota> {
        return notaDao.obtenerNotasPorUsuario(usuario)
    }

    suspend fun eliminarNota(id: Int): Boolean {
        val filas = notaDao.eliminarNota(id)
        return filas > 0
    }
}