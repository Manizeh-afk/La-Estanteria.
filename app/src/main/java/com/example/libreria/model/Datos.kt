package com.example.libreria.model

object Datos {
    val autores = listOf(
        Autor(1, "Gabriel García Márquez", "Colombiana",
            "Escritor de Cien años de soledad"),
        Autor(2, "Jorge Luis Borges", "Argentina",
            "Escritor de literatura fantástica"),
        Autor(3, "Jane Austen", "Británica",
            "Autora de novelas clásicas")
    )

    val libros = listOf(
        Libro(1, "Cien años de soledad", autores[0], "Realismo mágico", 85.0, true),
        Libro(2, "El Aleph", autores[1], "Cuento", 65.0, true),
        Libro(3, "Orgullo y prejuicio", autores[2], "Romance", 75.0, false)
    )
}
