package com.example.libreria.model

data class Autor(
    val id: Int,
    val nombre: String,
    val nacionalidad: String,
    val biografia: String
) {
    fun obtenerInformacion(): String = "$nombre - $nacionalidad"
}
