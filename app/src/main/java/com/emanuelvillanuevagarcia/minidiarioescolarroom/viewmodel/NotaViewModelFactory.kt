package com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emanuelvillanuevagarcia.minidiarioescolarroom.repositories.NotaRepository

class NotaViewModelFactory(
    private val repository: NotaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotaViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel desconocido")
    }
}