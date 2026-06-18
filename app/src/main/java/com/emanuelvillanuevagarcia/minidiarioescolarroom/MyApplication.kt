package com.emanuelvillanuevagarcia.minidiarioescolarroom

import android.app.Application
import com.emanuelvillanuevagarcia.minidiarioescolarroom.database.AppDatabase
import com.emanuelvillanuevagarcia.minidiarioescolarroom.repositories.NotaRepository
import com.emanuelvillanuevagarcia.minidiarioescolarroom.repositories.UsuarioRepository

class MyApplication : Application() {

    val database by lazy {
        AppDatabase.getDatabase(this)
    }

    val usuarioRepository by lazy {
        UsuarioRepository(database.usuarioDao())
    }

    val notaRepository by lazy {
        NotaRepository(database.notaDao())
    }
}