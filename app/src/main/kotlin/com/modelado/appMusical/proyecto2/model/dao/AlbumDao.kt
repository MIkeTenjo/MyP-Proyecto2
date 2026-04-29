package com.modelado.appMusical.proyecto2.model.dao

import com.modelado.appMusical.proyecto2.model.entities.Album

/**
 * Interfaz DAO para un objeto Album.
 * 
 * La clase es necesaria para separar la lógica de datos con la Interfaz
 * del cliente.
 * 
 * La clase ofrece acciones a implementar para la gestión de datos de 
 * los albums de los artistas en una base de datos musical.
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
    public fun insert(album: Album): Album?


    /**
     * Obtiene el Album específico dado su idenditificación(ID) en la base 
     * de datos.
     * 
     * @param [id] La identificación del Album en la base de datos.
     * 
     * @return El objeto de tipo Album relacionado con el id. Null si no
     * se encuentra tal ALbum.
     */
    public fun getById(id: Int): Album?

    /**
     * Busca un [Album] en la base de datos utilizando su nombre y la ruta del
     * directorio. 
     * 
     * Un Album se considera único al buscarlo dado su nombre [name] y el 
     * directorio [path] en donde se encuentran las rolas que la conforman. 
     * 
     * @param [name] El nombre del álbum a buscar.
     * 
     * @param [path] La ruta del directorio del album de las canciones
     * que posiblemente conformen al album a buscar. 
     * 
     * @return El [Album] encontrado en la base de datos. [Null] si
     * no hay un album similar.
     */
    public fun getByNameAndPath(name: String, path: String): Album?

    /**
     * Devuelve todos los albums en la base de datos.
     * 
     * @return Una lista con los albums contenidas en la base de datos.
     */
    public fun getAll(): List<Album>

    /**
     * Sobreescribe los datos de un ALbum que ya existe.
     * 
     * @param [album] El album a sobreescibir sus datos.
     * 
     * @return Un booleano que nos diga: [True] si el sobreescrito fue
     * éxitosa. [False] en otro caso.
     */
    public fun update(album: Album): Boolean

    /**
     * Borra el Album de la base de datos dado su ID.
     * 
     * @param [id] La identificación sincronizada al Album con intéres a borrar.
     * 
     * @return Un booleano que nos diga: [True] Si la eliminación del Album fue
     * éxitosa. [False] de otra manera.
     */
    public fun delete(id: Int): Boolean
}