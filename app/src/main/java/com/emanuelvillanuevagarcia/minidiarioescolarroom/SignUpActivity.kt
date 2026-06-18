package com.emanuelvillanuevagarcia.minidiarioescolarroom

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emanuelvillanuevagarcia.minidiarioescolarroom.databinding.ActivitySignUpBinding
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.UsuarioViewModel
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.UsuarioViewModelFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val usuarioViewModel: UsuarioViewModel by viewModels {
        UsuarioViewModelFactory(
            (application as MyApplication).usuarioRepository
        )
    }

    companion object {
        private const val CLAVE_USUARIO_REGISTRO = "clave_usuario_registro"
        private const val CLAVE_CONTRASENA_REGISTRO = "clave_contrasena_registro"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            binding.edtUsuarioRegistro.setText(savedInstanceState.getString(CLAVE_USUARIO_REGISTRO, ""))
            binding.edtContrasenaRegistro.setText(savedInstanceState.getString(CLAVE_CONTRASENA_REGISTRO, ""))
        }

        binding.btnGuardarRegistro.setOnClickListener {
            guardarRegistro()
        }

        binding.btnVolverLogin.setOnClickListener {
            finish()
        }
    }

    private fun guardarRegistro() {
        val usuario = binding.edtUsuarioRegistro.text.toString().trim()
        val contrasena = binding.edtContrasenaRegistro.text.toString().trim()

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        usuarioViewModel.registrarUsuario(usuario, contrasena) { guardado ->
            if (guardado) {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Este usuario ya existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CLAVE_USUARIO_REGISTRO, binding.edtUsuarioRegistro.text.toString())
        outState.putString(CLAVE_CONTRASENA_REGISTRO, binding.edtContrasenaRegistro.text.toString())
        super.onSaveInstanceState(outState)
    }
}