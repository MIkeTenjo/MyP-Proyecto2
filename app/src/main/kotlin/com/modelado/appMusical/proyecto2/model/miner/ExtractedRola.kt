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
    private val path: String,
    val performerName: String,
    val title: String,
    val albumName: String,
    val date: Int,
    val genre: String,
    val track: Int
)