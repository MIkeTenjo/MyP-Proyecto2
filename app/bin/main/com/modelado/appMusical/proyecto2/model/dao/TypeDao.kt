package com.modelado.appMusical.proyecto2.model.dao

import com.modelado.appMusical.proyecto2.model.entities.Type

/**
 * Interfaz DAO para un objeto que representa el tipo de artista musical
 * en la app musical.
 * 
 * La clase es necesaria para separar la lógica de datos con la Interfaz
 * del cliente.
 * 
 * La clase ofrece acciones a implementar para la gestión de datos en una
 * base de datos musical.
 */
interface TypeDao{

     /**
     * Método que recibe un objeto Type (un tipo de artista) 
     * y lo guarda en la datos de la app.
     * El método regresará un valor de tipo booleano que representará
     * el resultado del método.
     * 
     * @param [type] El tipo de artista a insertar en la base de datos.
     * 
     * @return False si hubo un error al insertar en la base de datos.
     * True si se tuvo éxito al insertar.
     */
    fun insert(type: Type): Boolean

    /**
     * Obtiene el tipo del artista específico dado su idenditificación(ID)
     * en la base de datos.
     * 
     * Se trabajará solo con los siguientes tipos de artistas:
     * 
     * 0- Persona
     * 
     * 1-Grupo
     * 
     * 2-Desconocido
     * 
     * @param [id] La identificación del tipo de artista en la base de datos.
     * 
     * @return El objeto de tipo Type relacionado con el id. Null si no se 
     * proporciono un id del 0-2.
     */
    fun getById(id: Int): Type?

    /**
     * Devuelve todos los tipos de artistas manejables en la base de datos.
     * 
     * @return Una lista con los tipos de artistas manejables en 
     * la base de datos.
     */
    fun getAll(): List<Type>

}