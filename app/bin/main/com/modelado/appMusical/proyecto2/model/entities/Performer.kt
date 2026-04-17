package com.modelado.appMusical.proyecto2.model.entities

/**
 * Clase que representa los diferentes tipos de artistas que se manejarán
 * en la app.
 * La clase solo tiene como entrada y salida datos acerca del tipo
 * de artista que se esta manejando, así como su identificación en la base
 * de datos.
 */
data class Performer(
    val id_performer: Int? = null,
    val id_Type: Int,
    val name: String
)