package com.modelado.appMusical.proyecto2.model.dao.sqlite

import com.modelado.appMusical.proyecto2.model.dao.TypeDao
import com.modelado.appMusical.proyecto2.model.entities.Type
import java.sql.Connection
import java.sql.ResultSet

class TypeDaoSQLite(private val connection: Connection) : TypeDao {

    /**
     * Convierte un registro de la tabla virtual sql
     * a un objeto de tipo Type. 
     * 
     * @param [rs] El registro sql de interés a convertir.
     * 
     * @return El objeto de tipo Type con los datos del registro interesado. 
     */
    private fun convertToType(rs: ResultSet): Type {
        return Type(
                    id_Type = rs.getInt("id_type"),
                    description = rs.getString("description")
        )
    }

     /**
     * Método que recibe un objeto Type (un tipo de interprete) 
     * y lo guarda en la datos de la app.
     * El método regresará un valor de tipo booleano que representará
     * el resultado del método.
     * 
     * @param [type] El tipo de interprete a insertar en la base de datos.
     * 
     * @return False si hubo un error al insertar en la base de datos.
     * True si se tuvo éxito al insertar.
     */
    override public fun insert(type: Type): Boolean {
        //si el Tipo de interprete existe, entonces lo ignorá. En otro caso, lo crea.
        val sql = "INSERT OR IGNORE INTO types (id_type, description) VALUES (?,?)"

        return try {// Regresa el valor de la condicional de la última linea(en este caso).
            connection.prepareStatement(sql).use { statement -> 
                statement.setInt(1, type.id_Type)
                statement.setString(2, type.description)
                statement.executeUpdate() >= 0 //Si se actualiza o no tiene que regresar True
            }//Se cierra la comunicación.
        }catch(e: Exception){
            println("Error al crear un Tipo de interprete: ${e.message}")
            false
        }
    }

    /**
     * Obtiene el tipo del interprete específico dado su idenditificación(ID)
     * en la base de datos.
     * 
     * Se trabajabá usualmente solo con los siguientes tipos de interpretes:
     * 
     * 0- Persona(solista)
     * 
     * 1-Grupo(Grupo de artistas)
     * 
     * 2-Desconocido
     * 
     * @param [id] La identificación del tipo de interprete en la base de datos.
     * 
     * @return El objeto de tipo Type relacionado con el id. Null si no se 
     * proporciono un id valido.
     */
    override public fun getById(id: Int): Type? {
        val sql = "SELECT id_type, description FROM types WHERE id_type = ?"

        return try{
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                val rs = statement.executeQuery()
                if(rs.next()) convertToType(rs) else null
            }
        } catch(e: Exception){
            println("Error al buscar Tipo por ID: ${e.message}")
            null
        }
    }

    /**
     * Devuelve todos los tipos de interpretes manejables en la base de datos.
     * 
     * @return Una lista con los tipos de interpretes manejables en 
     * la base de datos.
     */
    override public fun getAll(): List<Type> { 
        val listTypes = mutableListOf<Type>()
        val sql = "SELECT id_type, description FROM types"

        try{
            connection.createStatement().use{statement ->
                val rs = statement.executeQuery(sql)
                while(rs.next()){
                    listTypes.add(convertToType(rs))
                }

            }
        } catch(e: Exception){
            println("Error al obtener todos los Tipos de interpretes")
        }
        return listTypes
    }
}
