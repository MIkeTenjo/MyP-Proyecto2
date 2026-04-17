package com.modelado.appMusical.proyecto2.model.entities
/**
 * Clase que representa las "rolas" en la app.
 * La clase solo tiene como entrada y salida datos acerca de la canción
 * así como sus referencias en la base de datos.
 */
data class Rola(
    val id_rola: Int? = null,
    val id_performer: Int,
    val id_album: Int,
    val path: String,
    val title: String,
    val track: Int,
    val year: Int,
    val genre: String
)