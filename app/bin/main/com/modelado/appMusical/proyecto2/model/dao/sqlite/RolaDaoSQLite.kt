package com.modelado.appMusical.proyecto2.model.dao.sqlite

import com.modelado.appMusical.proyecto2.model.dao.RolaDao
import com.modelado.appMusical.proyecto2.model.entities.Rola
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

public class RolaDaoSQLite(private val connection: Connection): RolaDao{

    private fun convertToRola(rs: ResultSet): Rola{
        return Rola(
                    id_rola = rs.getInt("id_rola"),
                    id_performer = rs.getInt("id_performer"),
                    id_album = rs.getInt("id_album"), 
                    path = rs.getString("path"), 
                    title = rs.getString("title"), 
                    track = rs.getInt("track"), 
                    year = rs.getInt("year"), 
                    genre = rs.getString("genre"))
    }

    override public fun insert(rola: Rola): Rola?{
        val sql = "INSERT INTO rolas (id_performer, id_album, path, title, " +
                  "track, year, genre) VALUES (?, ?, ?, ?, ?, ?, ?)"

        return try{
            connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { statement ->
                statement.setInt(1, rola.id_performer)
                statement.setInt(2, rola.id_album)
                statement.setString(3, rola.path)
                statement.setString(4, rola.title)
                statement.setInt(5, rola.track)
                statement.setInt(6, rola.year)
                statement.setString(7, rola.genre)

                if(statement.executeUpdate() > 0){
                    statement.generatedKeys.use { rs ->
                        if(rs.next()){
                            return rola.copy(id_rola = rs.getInt(1))
                        }
                    }
                }
                null
            }
        }catch(e: Exception){
            println("Error al insertar la Rola: ${e.message}$")
            null
        }
    }

    override public fun getById(id: Int): Rola?{
        val sql = "SELECT * FROM rolas WHERE id_rola = ?"

        return try{
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                val rs = statement.executeQuery()
                if(rs.next()){
                    convertToRola(rs)
                }else{
                    null
                }
            }
        } catch(e: Exception){
            println("Error al buscar la Rola por ID: ${e.message}")
            null
        }
    }

    override public fun getAll(): List<Rola>{
        val lista = mutableListOf<Rola>()
        val sql = "SELECT * FROM rolas ORDER BY title ASC"
        try {
            connection.createStatement().use { statement ->
                val rs = statement.executeQuery(sql)
                while (rs.next()){
                  lista.add(convertToRola(rs))  
                } 
            }
        } catch (e: Exception) {
            println("Error al obtener todas las rolas: ${e.message}")
            return emptyList()
        }
        return lista
    }

    override public fun searchByTitle(title: String): List<Rola>{
        val tBuscado = "%$title%"
        val sql = "SELECT * FROM rolas WHERE title LIKE ? ORDER BY title ASC"
        val rolas = mutableListOf<Rola>()

        return try{
            connection.prepareStatement(sql).use{statement ->
                statement.setString(1, tBuscado)

                statement.executeQuery().use {rs ->
                    while(rs.next()){
                        rolas.add(convertToRola(rs))
                    }
                }
            }
            rolas
        }catch(e: Exception){
            println("Error al buscar las Rolas por título: {e.message}")
            emptyList()
        }
    }

    override public fun searchByGenre(genre: String): List<Rola>{
        val gBuscado = "%$genre%"
        val sql = "SELECT * FROM rolas WHERE genre LIKE ? ORDER BY title ASC"
        val rolas = mutableListOf<Rola>()

        return try{
            connection.prepareStatement(sql).use{statement ->
                statement.setString(1, gBuscado)

                statement.executeQuery().use {rs ->
                    while(rs.next()){
                        rolas.add(convertToRola(rs))
                    }
                }
            }
            rolas
        }catch(e: Exception){
            println("Error al buscar las Rolas por genéro: {e.message}")
            emptyList()
        }
    }
    
    override public fun searchByYear(year: Int): List<Rola>{
        val sql = "SELECT * FROM rolas WHERE year = ? ORDER BY id_album, track ASC"
        val rolas = mutableListOf<Rola>()

        return try {
            connection.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, year)
                
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        rolas.add(convertToRola(rs))
                    }
                }
            }
            rolas
        } catch (e: Exception) {
            println("Error al filtrar por género: ${e.message}")
            emptyList()
        }
    }

    override public fun update(rola: Rola): Boolean{
        val sql = """ UPDATE rolas SET id_performer = ?, id_album = ?,
                      path = ?. title = ?, track = ?, year  = ?, genre = ?
                      WHERE id_rola = ?
                  """.trimIndent()
        
        return try{
            connection.prepareStatement(sql).use{ statement ->
                statement.setInt(1, rola.id_performer)
                statement.setInt(2, rola.id_album)
                statement.setString(3, rola.path)
                statement.setString(4, rola.title)
                statement.setInt(5, rola.track)
                statement.setInt(6, rola.year)
                statement.setString(7, rola.genre)
                //Este es la la llave de la rola que se cambiará sus atributos
                statement.setInt(8, rola.id_rola)
                statement.executeUpdate() > 0 
            }
        }catch(e: Exception){
            print("Error: No se pudo actualizar los datos de la Rola. {e.message}")
            false
        }
    }

    override public fun delete(id: Int): Boolean{
        val sql = "DELETE FROM rolas WHERE id_rola = ?"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                statement.executeUpdate() > 0
            }
        } catch (e: Exception) {
            println("Error al eliminar la rola: ${e.message}")
            false
        }
    }
}