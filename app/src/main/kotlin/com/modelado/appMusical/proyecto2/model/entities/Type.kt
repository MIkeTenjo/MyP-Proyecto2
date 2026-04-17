package com.modelado.appMusical.proyecto2.model.entities

/**
 * Clase que representa el tipo de música con la cuál se clasificará 
 * en la base de datos.
 * La clase solo tiene como entrada y salida datos acerca de la
 * clasificación de la canción(rola) en la base de datos.
 */
data class Type(
    val id_Type: Int,
    val description: String
)