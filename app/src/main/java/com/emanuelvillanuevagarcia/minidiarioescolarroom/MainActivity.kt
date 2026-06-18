package com.emanuelvillanuevagarcia.minidiarioescolarroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emanuelvillanuevagarcia.minidiarioescolarroom.databinding.ActivityMainBinding
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.UsuarioViewModel
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.UsuarioViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val usuarioViewModel: UsuarioViewModel by viewModels {
        UsuarioViewModelFactory(
            (application as MyApplication).usuarioRepository
        )
    }

    companion object {
        private const val CLAVE_USUARIO = "clave_usuario"
        private const val CLAVE_CONTRASENA = "clave_contrasena"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            binding.edtUsuarioLogin.setText(savedInstanceState.getString(CLAVE_USUARIO, ""))
            binding.edtContrasenaLogin.setText(savedInstanceState.getString(CLAVE_CONTRASENA, ""))
        }

        binding.btnIniciarSesion.setOnClickListener {
            iniciarSesion()
        }

        binding.btnIrRegistro.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarSesion() {
        val usuario = binding.edtUsuarioLogin.text.toString().trim()
        val contrasena = binding.edtContrasenaLogin.text.toString().trim()

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Escribe usuario y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        usuarioViewModel.validarUsuario(usuario, contrasena) { correcto ->
            if (correcto) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CLAVE_USUARIO, binding.edtUsuarioLogin.text.toString())
        outState.putString(CLAVE_CONTRASENA, binding.edtContrasenaLogin.text.toString())
        super.onSaveInstanceState(outState)
    }
}