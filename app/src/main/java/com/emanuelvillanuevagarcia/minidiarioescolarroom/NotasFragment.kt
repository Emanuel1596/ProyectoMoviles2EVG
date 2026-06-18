package com.emanuelvillanuevagarcia.minidiarioescolarroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuelvillanuevagarcia.minidiarioescolarroom.adapter.NotaAdapter
import com.emanuelvillanuevagarcia.minidiarioescolarroom.databinding.FragmentNotasBinding
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Nota
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.NotaViewModel
import com.emanuelvillanuevagarcia.minidiarioescolarroom.viewmodel.NotaViewModelFactory
import kotlinx.coroutines.launch

class NotasFragment : Fragment() {

    private var _binding: FragmentNotasBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NotaAdapter

    private var usuario: String = ""

    private val notaViewModel: NotaViewModel by viewModels {
        NotaViewModelFactory(
            (requireActivity().application as MyApplication).notaRepository
        )
    }

    companion object {
        fun newInstance(usuario: String): NotasFragment {
            val fragment = NotasFragment()

            val bundle = Bundle()
            bundle.putString("usuario", usuario)

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("NotasFragment", "onCreate")

        usuario = arguments?.getString("usuario") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("NotasFragment", "onViewCreated")

        adapter = NotaAdapter(emptyList()) { nota ->
            eliminarNota(nota)
        }

        binding.rvNotas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotas.adapter = adapter

        binding.btnGuardarNota.setOnClickListener {
            guardarNota()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                notaViewModel.notas.collect { lista ->
                    adapter.actualizarNotas(lista)
                }
            }
        }

        notaViewModel.cargarNotas(usuario)
    }

    private fun guardarNota() {
        val titulo = binding.edtTituloNota.text.toString().trim()
        val contenido = binding.edtContenidoNota.text.toString().trim()

        if (titulo.isEmpty() || contenido.isEmpty()) {
            Toast.makeText(requireContext(), "Escribe título y contenido", Toast.LENGTH_SHORT).show()
            return
        }

        notaViewModel.agregarNota(usuario, titulo, contenido) { guardado ->
            if (guardado) {
                Toast.makeText(requireContext(), "Nota guardada", Toast.LENGTH_SHORT).show()

                binding.edtTituloNota.text.clear()
                binding.edtContenidoNota.text.clear()
            } else {
                Toast.makeText(requireContext(), "No se pudo guardar la nota", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun eliminarNota(nota: Nota) {
        notaViewModel.eliminarNota(nota.id, usuario)
        Toast.makeText(requireContext(), "Nota eliminada", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("NotasFragment", "onDestroyView")

        _binding = null
    }
}