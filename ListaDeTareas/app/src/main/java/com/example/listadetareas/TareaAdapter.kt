package com.example.listadetareas

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetareas.databinding.ItemTareaBinding

class TareaAdapter(
    private val tareas: MutableList<Tarea>,
    private val onEliminar: (Int) -> Unit,
    private val onCambioCompletada: () -> Unit,
    private val onEditarTitulo: (Int) -> Unit
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    // Cambiado a 'class' simple (sin inner) por mejores prácticas
    class TareaViewHolder(val binding: ItemTareaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val binding = ItemTareaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TareaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]

        holder.binding.tvTitulo.text = tarea.titulo
        holder.binding.tvCategoria.text = tarea.categoria

        // Limpiamos el listener previo para evitar disparos al reutilizar la celda.
        holder.binding.cbCompletada.setOnCheckedChangeListener(null)
        holder.binding.cbCompletada.isChecked = tarea.completada

        // Aplicar estilo inicial
        actualizarEstiloTexto(holder, tarea.completada)

        // Listener del CheckBox
        holder.binding.cbCompletada.setOnCheckedChangeListener { _, isChecked ->
            tarea.completada = isChecked
            actualizarEstiloTexto(holder, isChecked)
            onCambioCompletada()
        }

        // Listener del botón eliminar
        holder.binding.btnEliminar.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                onEliminar(currentPosition)
            }
        }

        holder.binding.btnEditar.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                onEditarTitulo(currentPosition)
            }
        }
    }

    override fun getItemCount(): Int = tareas.size

    private fun actualizarEstiloTexto(holder: TareaViewHolder, completada: Boolean) {
        if (completada) {
            holder.binding.tvTitulo.paintFlags =
                holder.binding.tvTitulo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.binding.tvTitulo.paintFlags =
                holder.binding.tvTitulo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
