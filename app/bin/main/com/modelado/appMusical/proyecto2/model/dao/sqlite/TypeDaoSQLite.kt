package com.modelado.appMusical.proyecto2.model.dao.sqlite

import com.modelado.appMusical.proyecto2.model.dao.TypeDao
import com.modelado.appMusical.proyecto2.model.entities.Type
import java.sql.Connection

class TypeDaoSQLite(private val connection: Connection) : TypeDao {

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
    override fun insert(type: Type): Boolean {
        return true
    }

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
    override fun getById(id: Int): Type? {
        return null
    }

    /**
     * Devuelve todos los tipos de artistas manejables en la base de datos.
     * 
     * @return Una lista con los tipos de artistas manejables en 
     * la base de datos.
     */
    override fun getAll(): List<Type>{
        return List(1) { Type(1, "c") }
    }
}
