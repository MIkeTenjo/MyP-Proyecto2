package com.modelado.appMusical.proyecto2.model.miner

/**
 * Clase que representa el objeto de tipo Rola que fue extraído 
 * por el minero.
 * 
 * La clase solo permite entrada y salida de datos de la rola
 * extraida, por lo que usa una data class.
 * 
 */
data class ExtractedRola(
    val path: String,
    val performer: String,
    val title: String,
    val album: String,
    val date: Int,
    val genre: String,
    val track: Int
    val duration: Int = 0
)