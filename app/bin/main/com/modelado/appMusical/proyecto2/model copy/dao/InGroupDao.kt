package com.modelado.appMusical.proyecto2.model.dao

import com.modelado.appMusical.proyecto2.model.entities.InGroup
import com.modelado.appMusical.proyecto2.model.entities.Person
import com.modelado.appMusical.proyecto2.model.entities.Group

/**
 * Interfaz DAO para un objeto InGroup que tendrá acciones para hacer
 * tipos de relaciones con los artistas en la app.
 * 
 * La clase es necesaria para separar la lógica de datos con la Interfaz
 * del cliente.
 * 
 * La clase ofrece acciones a implementar para la gestión de datos de los
 * artistas y las relaciones de los artistas.
 */
interface InGroupDao{

    /**
     * Método que recibe un objeto InGroup (Artistas en grupo) y
     * lo guarda en la base de datos de la app.
     * El método regresará un valor de tipo booleano que representará
     * el resultado de la acción.
     * 
     * @param [inGroup] El grupo de artistas (relacionados) a insertar
     * en la base de datos.
     * 
     * @return [False] si hubo un error al insertar en la base de datos.
     * [True] si se tuvo éxito al insertar.
     */
    fun insertRelation(inGroup: InGroup): Boolean

    /**
     * Obtiene una lista de objetos Person(Artistas solistas)
     * que tocan en un grupo músical.
     * 
     * @param [idGroup] El [ID] del grupo músical en donde se relacionan
     * los artistas solistas que son parte de la base de datos.
     * 
     * @return Una lista de Artistas solistas que conforman el
     * grupo músical.
     */
    fun getMembersOfGroup(idGroup: Int): List<Person>

    /**
     * Obtiene una lista de objetos Group(grupos músicales) en donde
     * conforma miembro un artista solista.
     * 
     * @param [idPerson] El [ID] del Artista solista que es miembro de
     * otros grupos músicales en la base de datos.
     * 
     * @return Una lista de grupos músicales en la que es miembro el
     * artista solista y que se encuentra en la base de datos.
     */
    fun getGroupsOfPerson(idPerson: Int): List<Group>


    /**
     * Borra la relación que tiene un solista con un grupo
     * músical y que se encuentran ambos en la base de datos.
     * 
     * @param [idPerson] El [ID] con el que se identifica a tal
     * Artista solista en la base de datos.
     * 
     * @param [idGroup] El [ID] con el que se identifica a tal grupo
     * músical en la base de datos.
     * 
     * @return Un objeto de tipo booleano que represente el resultado
     * de haber borrado la relación: [True] Si el resultado de borrar
     * la relación de la base de datos fue éxitosa. [False] en otro caso.
     * 
     */
    fun deletRelation(idPerson: Int, idGroup: Int): Boolean
}