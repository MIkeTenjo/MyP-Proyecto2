package com.modelado.appMusical.proyecto2.model.entities

/**
 * Clase que representa un grupo de artistas referenciados
 * en la base de datos.
 * La clase solo tiene datos de entrada y salida acerca de
 * los artistas referenciados en la base de datos.
 */
data class InGroup(
    val id_person: Int,
    val id_group: Int
)