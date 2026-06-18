package com.emanuelvillanuevagarcia.minidiarioescolarroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notas")
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usuario: String,
    val titulo: String,
    val contenido: String,
    val fecha: Date
)