package com.emanuelvillanuevagarcia.minidiarioescolarroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuelvillanuevagarcia.minidiarioescolarroom.databinding.ItemNotaBinding
import com.emanuelvillanuevagarcia.minidiarioescolarroom.model.Nota
import java.text.SimpleDateFormat
import java.util.Locale

class NotaAdapter(
    private var notas: List<Nota>,
    private val onEliminarClick: (Nota) -> Unit
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    class NotaViewHolder(val binding: ItemNotaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotaBinding.inflate(inflater, parent, false)

        return NotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]

        val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

        holder.binding.tvTituloItemNota.text = nota.titulo
        holder.binding.tvContenidoItemNota.text = nota.contenido
        holder.binding.tvFechaItemNota.text = formato.format(nota.fecha)

        holder.binding.btnEliminarNota.setOnClickListener {
            onEliminarClick(nota)
        }
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    fun actualizarNotas(nuevaLista: List<Nota>) {
        notas = nuevaLista
        notifyDataSetChanged()
    }
}