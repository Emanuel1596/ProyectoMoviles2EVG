package com.emanuelvillanuevagarcia.minidiarioescolarroom

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emanuelvillanuevagarcia.minidiarioescolarroom.databinding.ActivityProfileBinding
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.UsuarioViewModel
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.UsuarioViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private var usuario: String = ""
    private var ultimaConexionAnterior: String = ""

    private val usuarioViewModel: UsuarioViewModel by viewModels {
        UsuarioViewModelFactory(
            (application as MyApplication).usuarioRepository
        )
    }

    companion object {
        private const val CLAVE_USUARIO = "clave_usuario_perfil"
        private const val CLAVE_ULTIMA = "clave_ultima_conexion"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            usuario = savedInstanceState.getString(CLAVE_USUARIO, "")
            ultimaConexionAnterior = savedInstanceState.getString(CLAVE_ULTIMA, "")
            mostrarDatos()
        } else {
            usuario = intent.getStringExtra("usuario") ?: "Usuario"

            usuarioViewModel.obtenerUltimaConexion(usuario) { ultima ->
                ultimaConexionAnterior = ultima
                mostrarDatos()
                usuarioViewModel.actualizarUltimaConexion(usuario, obtenerFechaActual())
            }
        }

        binding.btnCerrarSesion.setOnClickListener {
            cerrarSesion()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, NotasFragment.newInstance(usuario))
                .commit()
        }
    }

    private fun mostrarDatos() {
        binding.tvBienvenida.text = "Bienvenido, $usuario"
        binding.tvUltimaConexion.text = "Última conexión: $ultimaConexionAnterior"
    }

    private fun cerrarSesion() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun obtenerFechaActual(): String {
        val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return formato.format(Date())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CLAVE_USUARIO, usuario)
        outState.putString(CLAVE_ULTIMA, ultimaConexionAnterior)
        super.onSaveInstanceState(outState)
    }
}