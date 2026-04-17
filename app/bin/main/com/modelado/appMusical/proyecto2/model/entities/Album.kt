package com.modelado.appMusical.proyecto2.model.entities

/**
 * Clase que representa los datos del album del artista que se manejan
 * en la app.
 * La clase solo tiene como entrada y salida datos acerca del album
 * del artista, además de su identificación en la base de datos.
 */
data class Album(
    val id_album: Int? = null,
    val path: String,
    val name: String,
    val year: Int
)