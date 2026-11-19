# Modelo de clases — La Estantería

## Clase Autor
Representa a los escritores registrados en la librería.

| Atributo | Tipo | Descripción |
|---|---|---|
| id | Int | Identificador del autor |
| nombre | String | Nombre completo |
| nacionalidad | String | Nacionalidad |
| biografia | String | Descripción biográfica |

Método: `obtenerInformacion(): String`.

## Clase Libro
Representa un libro del catálogo.

| Atributo | Tipo | Descripción |
|---|---|---|
| id | Int | Identificador |
| titulo | String | Título del libro |
| autor | Autor | Autor asociado |
| genero | String | Género literario |
| precio | Double | Precio en bolivianos |
| disponible | Boolean | Estado de disponibilidad |

Métodos: `obtenerInformacion(): String` y `cambiarDisponibilidad(): Unit`.

## Objeto Datos
Centraliza las colecciones `autores` y `libros` usadas por la aplicación.

## Relaciones
Cada instancia de Libro mantiene una referencia a un Autor. Un autor puede estar asociado a varios libros. Datos proporciona los objetos que consumen las pantallas.

## POO aplicada
Data classes, constructores, propiedades, métodos, objetos y asociación entre clases.
