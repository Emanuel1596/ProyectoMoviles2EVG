package com.emanuelvillanuevagarcia.minidiarioescolarroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emanuelvillanuevagarcia.minidiarioescolarroom.converters.Converters
import com.emanuelvillanuevagarcia.minidiarioescolarroom.dao.NotaDao
import com.emanuelvillanuevagarcia.minidiarioescolarroom.dao.UsuarioDao
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Nota
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Usuario

@Database(
    entities = [Usuario::class, Nota::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun notaDao(): NotaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mini_diario_escolar_room.db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}