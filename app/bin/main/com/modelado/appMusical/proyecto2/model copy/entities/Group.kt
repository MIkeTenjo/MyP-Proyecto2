package com.modelado.appMusical.proyecto2.model.entities

/**
 * Clase que representa los datos del grupo(grupo de artistas) 
 * que se manejanen la app.
 * La clase solo tiene como entrada y salida datos acerca del 
 * grupo con el que se refiere, además de su identificación en
 * la base de datos.
 */
data class Group(
    val id_group: Int,
    val name: String,
    val start_date: String,
    val end_date: String?
)