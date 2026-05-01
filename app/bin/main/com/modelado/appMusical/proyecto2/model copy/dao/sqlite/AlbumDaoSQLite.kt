package com.modelado.appMusical.proyecto2.model.dao.sqlite

import com.modelado.appMusical.proyecto2.model.dao.AlbumDao
import com.modelado.appMusical.proyecto2.model.entities.Album
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class AlbumDaoSQLite(private val connection: Connection) : AlbumDao {

    /**
     * Convierte un registro que contiene datos de un album de la
     * tabla virtual sql a un objeto de tipo Album. 
     * 
     * @param [rs] El registro sql de interés a convertir.
     * 
     * @return El objeto de tipo Album con los datos del registro interesado. 
     */
    private fun convertToAlbum(rs: ResultSet): Album {
        return Album(
            id_album = rs.getInt("id_album"),
            path = rs.getString("path"),
            name = rs.getString("name"),
            year = rs.getInt("year"))
    }

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
    override fun insert(album: Album): Album? {
        val sql = "INSERT INTO albums (path, name, year) VALUES (?, ?, ?)"
        try {
            connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { statement ->
   
                statement.setString(1, album.path)
                statement.setString(2, album.name)
                statement.setInt(3, album.year)

                if (statement.executeUpdate() > 0) {
                    statement.generatedKeys.use { rs ->
                        if (rs.next()) {
                            return album.copy(id_album = rs.getInt(1))
                        }
                    }
                }
                return null 
            }
        } catch (e: Exception) {
            println("Error al insertar el Album: ${e.message}")
            return null
        }
    }

    /**
     * Obtiene el Album específico dado su idenditificación(ID) en la base 
     * de datos.
     * 
     * @param [id] La identificación del Album en la base de datos.
     * 
     * @return El objeto de tipo Album relacionado con el id. Null si no
     * se encuentra tal ALbum.
     */
    override public fun getById(id: Int): Album?{
        val sql = "SELECT id_album, path, name, year FROM albums WHERE id_album = ?"

        return try{
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                val rs = statement.executeQuery()
                if(rs.next()) convertToAlbum(rs) else null
            }
        } catch(e: Exception){
            println("Error al buscar Album por ID: ${e.message}")
            null
        }
    }

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
     * @return El [Album] encontrado en la base de datos. `null` si
     * no hay un album similar.
     */
    override fun getByNameAndPath(name: String, path: String): Album? {
        val sql = "SELECT * FROM albums WHERE name = ? AND path = ?"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setString(1, name)
                statement.setString(2, path)
                val rs = statement.executeQuery()
                if (rs.next()){
                    convertToAlbum(rs)
                }else{
                    null
                }  
            }
        } catch (e: Exception) {
            println("Error al buscar Album por ID: ${e.message}")
            null
        }
    }

    /**
     * Devuelve todos los albums en la base de datos.
     * 
     * @return Una lista con los ALbums contenidas en la base de datos.
     */
    override public fun getAll(): List<Album>{
        val lista = mutableListOf<Album>()
        val sql = "SELECT * FROM albums"
        try {
            connection.createStatement().use { statement ->
                val rs = statement.executeQuery(sql)
                while (rs.next()){
                  lista.add(convertToAlbum(rs))  
                } 
            }
        } catch (e: Exception) {
            println("Error al obtener todos los albums: ${e.message}")
        }
        return lista
    }

    /**
     * Sobreescribe los datos de un ALbum que ya existe.
     * 
     * @param [album] El album a sobreescibir sus datos.
     * 
     * @return Un booleano que nos diga: [True] si el sobreescrito fue
     * éxitosa. [False] en otro caso.
     */
    override public fun update(album: Album): Boolean{
        val sql = "UPDATE albums SET path = ?, name = ?, year = ? WHERE id_album = ?"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setString(1, album.path)
                statement.setString(2, album.name)
                statement.setInt(3, album.year)
                statement.setInt(4, album.id_album)
                
                statement.executeUpdate() > 0
            }
        } catch (e: Exception) {
            println("Error al actualizar álbum: ${e.message}")
            false
        }
    }

    /**
     * Borra el Album de la base de datos dado su ID.
     * 
     * @param [id] La identificación sincronizada al Album con intéres a borrar.
     * 
     * @return Un booleano que nos diga: [True] Si la eliminación del Album fue
     * éxitosa. [False] de otra manera.
     */
    override public fun delete(id: Int): Boolean{
        val sql = "DELETE FROM albums WHERE id_album = ?"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                statement.executeUpdate() > 0
            }
        } catch (e: Exception) {
            println("Error al eliminar álbum: ${e.message}")
            false
        }
    }

}