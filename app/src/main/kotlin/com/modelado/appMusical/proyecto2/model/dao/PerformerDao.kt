package com.modelado.appMusical.proyecto2.model.dao

import com.modelado.appMusical.proyecto2.model.entities.Performer
import com.modelado.appMusical.proyecto2.model.entities.Person
import com.modelado.appMusical.proyecto2.model.entities.Group

/**
 * Interfaz DAO para un objeto Artista.
 * 
 * La clase es necesaria para separar la lógica de datos con la Interfaz
 * del cliente.
 * 
 * La clase ofrece acciones a implementar para la gestión de datos de los 
 * artistas en general o cada tipo de artista también tiene acciones
 * por separados, pero estás acciones se realizan en la base de datos.
 */
interface PerformerDao{

    /**
     * Método que recibe un objeto Artista que se sabe que es solista
     * y lo guarda en la base de datos de la app.
     * El método regresará un valor de tipo booleano que representará
     * el resultado de la acción.
     * 
     * @param [performer] El artista solista a insertar en la base de datos.
     * 
     * @param [person] El mismo objeto artista a insertar en la base de datos
     * pero de tipo Person(solista).
     * 
     * @return [False] si hubo un error al insertar en la base de datos.
     * [True] si se tuvo éxito al insertar.
     */
    fun insertPerson(performer: Performer, person: Person): Boolean

    /**
     * Método que recibe un objeto Artista que se sabe que es un grupo
     * de artistas y lo guarda en la base de datos de la app.
     * El método regresará un valor de tipo booleano que representará
     * el resultado de la acción.
     * 
     * @param [performer] El grupo Artista a insertar en la base de datos.
     * 
     * @param [person] El mismo objeto Artista (grupo Artista) a insertar 
     * en la base de datos pero de tipo Group.
     * 
     * @return [False] si hubo un error al insertar en la base de datos.
     * [True] si se tuvo éxito al insertar.
     */
    fun insertGroup(performer: Performer, group: Group): Boolean

    /**
     * Obtiene el Artista(o grupo Artista) específico dada su 
     * idenditificación(ID) en la base de datos.
     * 
     * @param [id] La identificación del artista en la base de datos.
     * 
     * @return El objeto de tipo Artista relacionado con el [id]. Null si no
     * se encuentra tal Artista.
     */
    fun getById(id: Int): Performer?

    /**
     * Obtiene el Artista solista específico dada su 
     * idenditificación(ID) en la base de datos.
     * 
     * @param [id] La identificación del artista solista en la base de datos.
     * 
     * @return El objeto de tipo Person(solista) relacionado con el [id]
     * con todos y sus detalles(datos). Null si nose encuentra tal Artista.
     */
    fun getPersonDetails(id: Int): Person?

    /**
     * Obtiene el grupo Artista específico dada su 
     * idenditificación(ID) en la base de datos.
     * 
     * @param [id] La identificación del grupo artista 
     * en la base de datos.
     * 
     * @return El objeto de tipo Group(grupo Artista) relacionado 
     * con el [id] con todo y sus datos. Null si nose encuentra
     * tal grupo Artista.
     */
    fun getGroupDetails(id: Int): Group?

    /**
     * Devuelve todos los Artistas (incluyendo grupos de Artistas) 
     * en la base de datos.
     * 
     * @return Una lista con todos los artistas contenidos en la base de datos.
     */
    fun getAll(): List<Performer>

    /* En duda si poner getAllPerson y getAllGroup */

    /**
     * Sobreescribe el nombre de referencia del artista (o un grupo artista)
     * que ya existe en la base de datos.
     * 
     * @param [performer] El artista a sobreescibir su referencia en la base
     * de datos.
     * 
     * @return Un objeto booleano que representa el resultado de
     * sobreescribir al artista: [True] si el sobreescrito fue
     * éxitoso. [False] en otro caso.
     */
    fun update(performer: Performer): Boolean

    /**
     * Sobreescribe los datos del artista solista
     * que ya existe en la base de datos.
     * 
     * @param [person] El artista a sobreescibir sus datos en la base
     * de datos.
     * 
     * @return Un objeto booleano que representa el resultado de
     * sobreescribir al artista solita: [True] si el sobreescrito fue
     * éxitoso. [False] en otro caso.
     */
    fun updatePersonDetails(person: Person): Boolean

    /**
     * Sobreescribe los datos del grupo artista
     * que ya existe en la base de datos.
     * 
     * @param [person] El grupo artista a sobreescibir sus datos 
     * en la base de datos.
     * 
     * @return Un objeto booleano que representa el resultado de
     * sobreescribir al grupo artista: [True] si el sobreescrito fue
     * éxitoso. [False] en otro caso.
     */
    fun updateGroupDetails(group: Group): Boolean

    /**
     * Borra el artista de la base de datos dado su ID.
     * 
     * @param [id] La identificación sincronizada al artista
     * con intéres a borrar.
     * 
     * @return Un objeto de tipo booleano que representa el resultado
     * de haber eliminado el artista: [True] Si la eliminación del
     * artista fue éxitosa. [False] de otra manera.
     */
    fun delete(id: Int): Boolean
}