package com.modelado.appMusical.proyecto2.model.dao

import com.modelado.appMusical.proyecto2.model.entities.Rola

/**
 * Interfaz DAO para un objeto Rola(Canción).
 * 
 * La clase es necesaria para separar la lógica de datos con la Interfaz
 * del cliente.
 * 
 * La clase ofrece acciones a implementar para la gestión de datos de las 
 * canciones en una base de datos musical.
 */
interface RolaDao{

    /**
     * Método que recibe un objeto Rola y lo guarda en la base de 
     * datos de la app.
     * El método regresará un valor de tipo booleano que representará
     * el resultado de la acción.
     * 
     * @param [Rola] La canción a insertar en la base de datos.
     * 
     * @return [False] si hubo un error al insertar en la base de datos.
     * [True] si se tuvo éxito al insertar.
     */
    fun insert(rola: Rola): Boolean

    /**
     * Obtiene la canción específica dada su idenditificación(ID) 
     * en la base de datos.
     * 
     * @param [id] La identificación de la canción en la base de datos.
     * 
     * @return El objeto de tipo Rola relacionado con el [id]. Null si no
     * se encuentra tal Canción.
     */
    fun getById(id: Int): Rola?

    /**
     * Devuelve todas las canciones en la base de datos.
     * 
     * @return Una lista con las canciones contenidas en la base de datos.
     */
    fun getAll(): List<Rola>

    /**
     * Sobreescribe los datos de una canción que ya existe en la base de datos.
     * 
     * @param [Rola] La canción a sobreescibir sus datos.
     * 
     * @return Un objeto booleano que representa el resultado de
     * sobreescribir la canción: [True] si el sobreescrito fue
     * éxitoso. [False] en otro caso.
     */
    fun update(rola: Rola): Boolean

    /**
     * Borra la canción de la base de datos dado su ID.
     * 
     * @param [id] La identificación sincronizada a la canción
     * con intéres a borrar.
     * 
     * @return Un objeto de tipo booleano que representa el resultado
     * de haber eliminado la canción: [True] Si la eliminación de la
     * canción fue éxitosa. [False] de otra manera.
     */
    fun delete(id: Int): Boolean
}