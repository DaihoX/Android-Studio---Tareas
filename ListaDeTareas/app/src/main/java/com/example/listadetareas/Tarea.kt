package com.example.listadetareas

data class Tarea(
    val id: Int,
    var titulo: String,
    var categoria: String = "Personal",
    var completada: Boolean = false
)