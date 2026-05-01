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
     * @return La [Rola] que se agregó por último a la base de datos.
     * 'null' si no se logró agregar la Rola.
     */
    public fun insert(rola: Rola): Rola?

    /**
     * Obtiene la canción específica dada su idenditificación(ID) 
     * en la base de datos.
     * 
     * @param [id] La identificación de la canción en la base de datos.
     * 
     * @return El objeto de tipo Rola relacionado con el [id]. Null si no
     * se encuentra tal Canción.
     */
    public fun getById(id: Int): Rola?

    /**
     * Devuelve todas las canciones en la base de datos.
     * 
     * @return Una lista con las canciones contenidas en la base de datos.
     */
    public fun getAll(): List<Rola>

    /**
     * Busca las Rolas en la base de datos con un titúlo en especifico y la
     * regresa en una lista. EL titúlo de la Rola puede no tener el titúlo 
     * exacto buscado, pero si contenerla.
     * 
     * @param [title] El titúlo de las canciones con interés a buscar. 
     * 
     * @return Una lista con todas las Rolas contenidas en la base de datos
     * que contengan como titúlo el titúlo buscado.
     */
    public fun searchByTitle(title: String): List<Rola>

    /**
     * Busca las Rolas en la base de datos con un tipo de genéro en especifico 
     * y la regresa en una lista. EL genéro de la Rola puede no ser similar, pero
     * si contenerla. Si hay un genéro llamado "Rock" en la base de datos y el
     * usuario busca "Rock1" puede no encontrarse, en cambio, si hay un genéro
     * llamado "Rock_" y el usuario busca "Rock" se mostrará "Rock_" como uno de 
     * los genéros buscados.
     * 
     * @param [genre] El genéro de las canciones con interés a buscar. 
     * 
     * @return Una lista con todas las Rolas contenidas en la base de datos
     * que contengan como genéro el genéro buscado.
     */
    public fun searchByGenre(genre: String): List<Rola>

    /**
     * Busca las rolas en la base de datos que comparten un año en especifico.
     * 
     * Las rolas que devuelva el método estarán ordenadas Dado su album y la
     * secuencia en la que fueron públicadas. 
     * 
     * @param [year] El año en el que fueron creadas las rolas con interés a buscar. 
     * 
     * @return Una lista con las Rolas que comparten el mismo año en el fueron públicadas
     */
    public fun searchByYear(year: Int): List<Rola>

    /**
     * Sobreescribe los datos de una canción que ya existe en la base de datos.
     * 
     * @param [Rola] La canción a sobreescibir sus datos.
     * 
     * @return Un objeto booleano que representa el resultado de
     * sobreescribir la canción: [True] si el sobreescrito fue
     * éxitoso. [False] en otro caso.
     */
    public fun update(rola: Rola): Boolean

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
    public fun delete(id: Int): Boolean
}