package com.example.agromarketapp

data class Tienda(
    val nombre: String,
    val descripcion: String,
    val imagenUri: String? = null,
    val productos: MutableList<Producto> = mutableListOf(),
    val ubicacion: String = "",
    val horario: String = "",
    val contacto: String = "",
    val categoria: String = ""
)