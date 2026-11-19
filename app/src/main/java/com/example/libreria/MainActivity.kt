package com.example.libreria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.libreria.model.Datos
import com.example.libreria.ui.theme.LibreriaTheme

private val Crema = Color(0xFFF5F0E8)
private val Cafe = Color(0xFF493629)
private val Terracota = Color(0xFFB66B4E)
private val Texto = Color(0xFF3D352F)
private val Gris = Color(0xFF89827C)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LibreriaTheme { AplicacionLibreria() } }
    }
}

@Composable
fun AplicacionLibreria() {
    var pantalla by remember { mutableIntStateOf(0) }
    Scaffold(
        containerColor = Crema,
        bottomBar = {
            NavigationBar(containerColor = Cafe, contentColor = Color.White) {
                NavigationBarItem(
                    selected = pantalla == 0,
                    onClick = { pantalla = 0 },
                    icon = { Icon(Icons.Outlined.MenuBook, contentDescription = "Catálogo") },
                    label = { Text("Catálogo") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        indicatorColor = Terracota,
                        unselectedTextColor = Color.LightGray
                    )
                )
                NavigationBarItem(
                    selected = pantalla == 1,
                    onClick = { pantalla = 1 },
                    icon = { Icon(Icons.Outlined.PersonOutline, contentDescription = "Autores") },
                    label = { Text("Autores") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        indicatorColor = Terracota,
                        unselectedTextColor = Color.LightGray
                    )
                )
            }
        }
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding)) {
            when (pantalla) {
                0 -> PantallaLibros()
                1 -> PantallaAutores()
            }
        }
    }
}

@Composable
fun Encabezado(titulo: String, subtitulo: String) {
    Column(Modifier.padding(bottom = 22.dp)) {
        Text("LA ESTANTERÍA", color = Terracota, fontSize = 12.sp,
            fontWeight = FontWeight.Bold, letterSpacing = 3.sp)
        Spacer(Modifier.height(10.dp))
        Text(titulo, color = Cafe, fontSize = 29.sp, fontWeight = FontWeight.Bold,
            lineHeight = 35.sp)
        Spacer(Modifier.height(5.dp))
        Text(subtitulo, color = Gris, fontSize = 14.sp)
    }
}

@Composable
fun PantallaLibros() {
    var busqueda by remember { mutableStateOf("") }
    val librosFiltrados = Datos.libros.filter {
        it.titulo.contains(busqueda, ignoreCase = true) ||
            it.autor.nombre.contains(busqueda, ignoreCase = true)
    }
    Column(Modifier.fillMaxSize().padding(horizontal = 20.dp).padding(top = 28.dp)) {
        Encabezado("Un mundo de historias", "Encuentra tu próxima lectura favorita.")
        Card(
            Modifier.fillMaxWidth().padding(bottom = 20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Cafe)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("BIENVENIDO A NUESTRA LIBRERÍA", color = Color(0xFFE8B99D),
                    fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                Spacer(Modifier.height(10.dp))
                Text("Cada libro es una nueva aventura.", color = Color.White,
                    fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
        OutlinedTextField(
            value = busqueda, onValueChange = { busqueda = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Buscar libro o autor...") },
            singleLine = true, shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Terracota, unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
            )
        )
        Spacer(Modifier.height(20.dp))
        Text("Nuestro catálogo", color = Cafe, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 20.dp)) {
            items(librosFiltrados) { libro ->
                Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(Modifier.padding(18.dp)) {
                        Text(libro.titulo, color = Cafe, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text("Por ${libro.autor.nombre}", color = Terracota, fontSize = 14.sp)
                        Spacer(Modifier.height(5.dp))
                        Text(libro.genero, color = Gris, fontSize = 13.sp)
                        Spacer(Modifier.height(14.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text("Bs. ${libro.precio}", color = Cafe, fontSize = 17.sp,
                                fontWeight = FontWeight.Bold)
                            Text(if (libro.disponible) "Disponible" else "Agotado",
                                color = if (libro.disponible) Color(0xFF388451) else Terracota,
                                fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            if (librosFiltrados.isEmpty()) {
                item { Text("No se encontraron libros.", color = Gris, modifier = Modifier.padding(12.dp)) }
            }
        }
    }
}

@Composable
fun PantallaAutores() {
    Column(Modifier.fillMaxSize().padding(horizontal = 20.dp).padding(top = 28.dp)) {
        Encabezado("Nuestros autores", "Las mentes detrás de cada historia.")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 20.dp)) {
            items(Datos.autores) { autor ->
                Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(Modifier.padding(20.dp)) {
                        Text(autor.nombre, color = Cafe, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text(autor.nacionalidad, color = Terracota, fontSize = 14.sp)
                        Spacer(Modifier.height(10.dp))
                        Text(autor.biografia, color = Texto, fontSize = 14.sp)
                        Spacer(Modifier.height(12.dp))
                        Text("Libros registrados: ${Datos.libros.count { it.autor.id == autor.id }}",
                            color = Gris, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
