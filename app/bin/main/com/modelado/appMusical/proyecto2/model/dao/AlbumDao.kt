package com.modelado.appMusical.proyecto2.model.dao

import com.modelado.appMusical.proyecto2.model.entities.Album

/**
 * Interfaz DAO para un objeto Album.
 * 
 * La clase es necesaria para separar la lógica de datos con la Interfaz
 * del cliente.
 * 
 * La clase ofrece acciones a implementar para la gestión de datos de 
 * los albumes de los artistas en una base de datos musical.
 */
interface AlbumDao{

    /**
     * Método que recibe un objeto Album y lo guarda en la base de 
     * datos de la app.
     * El método regresará un valor de tipo booleano que representará
     * el resultado del método.
     * 
     * @param [Album] El album a insertar en la base de datos.
     * 
     * @return False si hubo un error al insertar en la base de datos.
     * True si se tuvo éxito al insertar.
     */
    fun insert(album: Album): Boolean

    /**
     * Obtiene el Album específico dado su idenditificación(ID) en la base 
     * de datos.
     * 
     * @param [id] La identificación del Album en la base de datos.
     * 
     * @return El objeto de tipo Album relacionado con el id. Null si no
     * se encuentra tal ALbum.
     */
    fun getById(id: Int): Album?

    /**
     * Devuelve todos los albumes en la base de datos.
     * 
     * @return Una lista con los ALbumes contenidas en la base de datos.
     */
    fun getAll(): List<Album>

    /**
     * Sobreescribe los datos de un ALbum que ya existe.
     * 
     * @param [album] El album a sobreescibir sus datos.
     * 
     * @return Un booleano que nos diga: [True] si el sobreescrito fue
     * éxitosa. [False] en otro caso.
     */
    fun update(album: Album): Boolean

    /**
     * Borra el Album de la base de datos dado su ID.
     * 
     * @param [id] La identificación sincronizada al Album con intéres a borrar.
     * 
     * @return Un booleano que nos diga: [True] Si la eliminación del Album fue
     * éxitosa. [False] de otra manera.
     */
    fun delete(id: Int): Boolean
}