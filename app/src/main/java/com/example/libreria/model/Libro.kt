package com.example.libreria.model

data class Libro(
    val id: Int,
    val titulo: String,
    val autor: Autor,
    val genero: String,
    val precio: Double,
    var disponible: Boolean
) {
    fun obtenerInformacion(): String =
        "$titulo - ${autor.nombre} - $genero - Bs. $precio"

    fun cambiarDisponibilidad() {
        disponible = !disponible
    }
}
