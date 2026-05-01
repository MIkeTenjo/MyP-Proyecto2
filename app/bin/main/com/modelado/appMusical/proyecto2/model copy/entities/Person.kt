package com.modelado.appMusical.proyecto2.model.entities

/**
 * Clase que representa los datos del artista(solista) que se manejan
 * en la app.
 * La clase solo tiene como entrada y salida datos acerca del
 * artista con el que se refiere, además de la identificación
 * en la base de datos.
 */
data class Person(
    val id_person: Int,
    val stage_name: String,
    val real_name: String,
    val birth_date: String,
    val death_date: String?
)