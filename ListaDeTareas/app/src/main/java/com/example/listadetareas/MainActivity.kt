package com.example.listadetareas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadetareas.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listaTareas = mutableListOf<Tarea>()
    private lateinit var adapter: TareaAdapter
    private var contadorId = 0

    companion object {
        private const val PREFS_NAME = "tareas_prefs"
        private const val KEY_TAREAS = "tareas_json"
        private const val KEY_CONTADOR = "contador_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarDatos()
        configurarSpinnerCategorias()
        configurarRecyclerView()
        configurarBotones()
        actualizarContador()
    }

    private fun configurarRecyclerView() {
        adapter = TareaAdapter(
            tareas = listaTareas,
            onEliminar = { posicion -> eliminarTarea(posicion) },
            onCambioCompletada = {
                actualizarContador()
                guardarDatos()
            },
            onEditarTitulo = { posicion -> mostrarDialogoEditarTitulo(posicion) }
        )

        binding.rvTareas.layoutManager = LinearLayoutManager(this)

        binding.rvTareas.adapter = adapter
    }

    private fun configurarBotones() {
        binding.btnAgregar.setOnClickListener {
            val texto = binding.etNuevaTarea.text.toString().trim()
            if (texto.isNotEmpty()) {
                val categoriaSeleccionada = binding.spCategoria.selectedItem.toString()
                agregarTarea(texto, categoriaSeleccionada)
                binding.etNuevaTarea.text.clear()
            } else {
                Toast.makeText(this, "Escribe una tarea primero", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun configurarSpinnerCategorias() {
        val categorias = resources.getStringArray(R.array.categorias_tarea)
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categorias
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategoria.adapter = spinnerAdapter
    }

    private fun agregarTarea(titulo: String, categoria: String) {
        contadorId++

        val nuevaTarea = Tarea(id = contadorId, titulo = titulo, categoria = categoria)

        listaTareas.add(nuevaTarea)

        adapter.notifyItemInserted(listaTareas.size - 1)

        actualizarContador()
        guardarDatos()
    }

    private fun eliminarTarea(posicion: Int) {
        listaTareas.removeAt(posicion)

        adapter.notifyItemRemoved(posicion)
        adapter.notifyItemRangeChanged(posicion, listaTareas.size)

        actualizarContador()
        guardarDatos()
    }

    private fun mostrarDialogoEditarTitulo(posicion: Int) {
        val tarea = listaTareas[posicion]
        val input = EditText(this).apply {
            setText(tarea.titulo)
            setSelection(tarea.titulo.length)
            hint = getString(R.string.hint_editar_titulo)
        }

        AlertDialog.Builder(this)
            .setTitle(R.string.titulo_editar_tarea)
            .setView(input)
            .setPositiveButton(R.string.guardar) { _, _ ->
                val nuevoTitulo = input.text.toString().trim()
                if (nuevoTitulo.isEmpty()) {
                    Toast.makeText(this, R.string.error_titulo_vacio, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                tarea.titulo = nuevoTitulo
                adapter.notifyItemChanged(posicion)
                guardarDatos()
            }
            .setNegativeButton(R.string.cancelar, null)
            .show()
    }

    private fun actualizarContador() {
        val pendientes = listaTareas.count { !it.completada }

        binding.tvContador.text = "$pendientes tareas pendientes"
    }

    private fun guardarDatos() {
        val tareasJson = JSONArray()
        listaTareas.forEach { tarea ->
            val obj = JSONObject().apply {
                put("id", tarea.id)
                put("titulo", tarea.titulo)
                put("categoria", tarea.categoria)
                put("completada", tarea.completada)
            }
            tareasJson.put(obj)
        }

        getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit {
            putString(KEY_TAREAS, tareasJson.toString())
            putInt(KEY_CONTADOR, contadorId)
        }
    }

    private fun cargarDatos() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        contadorId = prefs.getInt(KEY_CONTADOR, 0)

        val tareasString = prefs.getString(KEY_TAREAS, null) ?: return
        try {
            val tareasJson = JSONArray(tareasString)
            listaTareas.clear()

            for (i in 0 until tareasJson.length()) {
                val obj = tareasJson.getJSONObject(i)
                val tarea = Tarea(
                    id = obj.optInt("id", 0),
                    titulo = obj.optString("titulo", ""),
                    categoria = obj.optString("categoria", "Personal"),
                    completada = obj.optBoolean("completada", false)
                )
                listaTareas.add(tarea)
            }
        } catch (_: Exception) {
            listaTareas.clear()
        }
    }
}