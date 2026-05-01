package com.modelado.appMusical.proyecto2.model.dao.sqlite

import com.modelado.appMusical.proyecto2.model.dao.PerformerDao
import com.modelado.appMusical.proyecto2.model.entities.Performer
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

public class PerformerDaoSQLite(private val connection: Connection): PerformerDao{
    private fun convertToPerformer(rs: ResultSet): Performer{
        return Performer(
                    id_performer = rs.getInt("id_performer"),
                    name = rs.getString("name"))
    }
|
    override public fun insertPerson(performer: Performer, person: Person) Person?{
        val sqlPerformer = "INSERT INTO performers (name) VALUES (?)"
        val sqlPerson = "INSERT INTO persons (id_performer, stage_name, real_name, birth_date) VALUES (?, ?, ?, ?)"

        return try {
            connection.autoCommit = false // Desactivamos el auto-commit para manejar la transacción manualmente
            val id = connection.prepareStatement(sqlPerformer, Statement.RETURN_GENERATED_KEYS).use { statement ->
                statement.setString(1, performer.name)
                statement.executeUpdate()

                val rs = statement.generatedKeys
                if (rs.next()) {
                    rs.getInt(1) // Devuelve el ID generado para el performer
                } else {
                    throw Exception("No se pudo obtener el ID del performer insertado.")
                }
            }

            connection.prepareStatement(sqlPerson).use { statement ->
                statement.setInt(1, id)
                statement.setString(2, person.stage_name)
                statement.setString(3, person.real_name)
                statement.setString(4, person.birth_date)
                statement.executeUpdate()
            } 

            connection.commit() // Si todo va bien, confirmamos la transacción
            person.copy(id_person = id) // Devolvemos el objeto Person con el ID asignado
        }catch(Exception e) {
            connection.rollback() // Si ocurre un error
            println("Error al insertar el Performer y Person: ${e.message}")
            null
        }finally {
            connection.autoCommit = true // Volvemos a activar el auto-commit
        }
    }

    override public fun insertGroup(performer: Performer, group: Group): Group?{
        val sqlPerformer = "INSERT INTO performers (name) VALUES (?)"
        val sqlGroup = "INSERT INTO groups (id_performer, start_date, end_date) VALUES (?, ?, ?)" 

        return try {
            connection.autoCommit = false // Desactivamos el auto-commit

            val id = connection.prepareStatement(sqlPerformer, Statement.RETURN_GENERATED_KEYS).use { statement ->
                statement.setString(1, performer.name)
                statement.executeUpdate() //insertamos y actualizamos el performer

                val rs = statement.generatedKeys
                if (rs.next()) {
                    rs.getInt(1) // Devuelve el ID generado para el performer
                } else {
                    throw Exception("No se pudo obtener el ID del performer insertado.")
                }
            }

            connection.prepareStatement(sqlGroup).use { statement ->
                statement.setInt(1, id)
                statement.setString(2, group.start_date)
                if(group.end_date != null) {
                    statement.setString(3, group.end_date)
                } else {
                    statement.setNull(3, java.sql.Types.VARCHAR) // Si end_date es null, lo establecemos como NULL en la base de datos
                }
                statement.executeUpdate() //insertamos y actualizamos el grupo
            }

            connection.commit() // Si todo va bien, confirmamos la transacción
            group.copy(id_group = id) // Devolvemos el objeto Group con el ID asignado
        }catch(Exception e) {
            connection.rollback() // Si ocurre un error
            println("Error al insertar el Performer y Group: ${e.message}")
            null
        }finally {
            connection.autoCommit = true // Volvemos a activar el auto-commit
        }
    }

    override public fun getById(id: Int): Performer?{
        val sql = "SELECT * FROM performers WHERE id_performer = ?"
        return  try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                statement.executeQuery().use { rs ->
                    if (rs.next()){
                        convertToPerformer(rs)
                    }else {
                        null
                    }
                }
            }
        }catch(Exception e) {
            println("Error al obtener el Performer por ID: ${e.message}")
            null
        }
    }

    override fun getPersonDetails(id: Int): Person? {
        // Buscamos directamente en la tabla de personas usando el id_performer como llave de enlace
        val sql = """
                  SELECT id_person, stage_name, real_name, birth_date, death_date 
                  FROM persons 
                  WHERE id_performer = ?
                  """.trimIndent()
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                statement.executeQuery().use { rs ->
                    if (rs.next()) {
                        Person(
                            id_person = rs.getInt("id_person"),
                            stage_name = rs.getString("stage_name"),
                            real_name = rs.getString("real_name"),
                            birth_date = rs.getString("birth_date"),
                            death_date = rs.getString("death_date") // Esto puede ser null
                        )
                    } else{
                        null
                    }
                }
            }
        } catch (e: Exception) {
            println("Error al obtener detalles de la persona: ${e.message}")
            null
        }
    }

    override fun getAll(): List<Performer> {
        val sql = "SELECT * FROM performers ORDER BY name ASC"
        val performers = mutableListOf<Performer>()

        return try {
            connection.createStatement().use { statement ->
                statement.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        performers.add(convertToPerformer(rs))
                    }
                }
            }
            performers
        } catch (e: Exception) {
            println("Error al obtener la lista de artistas: ${e.message}")
            emptyList()
        }
    }

    override fun update(performer: Performer): Boolean {
        val sql = "UPDATE performers SET name = ? WHERE id_performer = ?"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setString(1, performer.name)
                statement.setInt(2, performer.idPerformer)
                
                statement.executeUpdate() > 0
            }
        } catch (e: Exception) {
            println("Error al actualizar el nombre del artista: ${e.message}")
            false
        }
    }

    override fun updatePersonDetails(person: Person): Boolean {
        val sql = """
            UPDATE persons 
            SET stage_name = ?, real_name = ?, birth_date = ?, death_date = ? 
            WHERE id_person = ?
        """.trimIndent()
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setString(1, person.stage_name)
                statement.setString(2, person.real_name)
                statement.setString(3, person.birth_date)
                
                // Manejo de valores nulos para la fecha de muerte
                if (person.death_date != null) {
                    statement.setString(4, person.death_date)
                } else {
                    statement.setNull(4, java.sql.Types.VARCHAR)
                }
                
                statement.setInt(5, person.id_person)
                
                statement.executeUpdate() > 0
            }
        } catch (e: Exception) {
            println("Error al actualizar los detalles de la persona: ${e.message}")
            false
        }
    }

    override fun updateGroupDetails(group: Group): Boolean {
        val sqlPerformer = """
            UPDATE performers 
            SET name = ? 
            WHERE id_performer = (
                SELECT id_performer FROM groups WHERE id_group = ?
            )
        """.trimIndent()

        val sqlGroup = """
            UPDATE groups 
            SET start_date = ?, end_date = ? 
            WHERE id_group = ?
        """.trimIndent()

        return try {
            connection.autoCommit = false // Iniciamos transacción

            // 1. Actualizar el nombre del artista en la tabla 'performers'
            connection.prepareStatement(sqlPerformer).use { statement ->
                statement.setString(1, group.name)
                statement.setInt(2, group.id_group)
                statement.executeUpdate()
            }

            // 2. Actualizar los detalles del grupo en la tabla 'groups'
            connection.prepareStatement(sqlGroup).use { statement ->
                statement.setString(1, group.start_date)
                
                // Manejo de fecha de término (puede ser nulo si la banda sigue activa)
                if (group.end_date != null) {
                    statement.setString(2, group.end_date)
                } else {
                    statement.setNull(2, java.sql.Types.VARCHAR)
                }
                
                statement.setInt(3, group.id_group)
                statement.executeUpdate()
            }

            connection.commit() // Confirmamos cambios
            true
        } catch (e: Exception) {
            try {
                connection.rollback() // Deshacemos todo si algo falla
            } catch (rollbackEx: Exception) {
                println("Error al realizar rollback: ${rollbackEx.message}")
            }
            println("Error al actualizar los detalles del grupo: ${e.message}")
            false
        } finally {
            connection.autoCommit = true // Restauramos conexión
        }
    }

    override fun delete(id: Int): Boolean {
        val sql = "DELETE FROM performers WHERE id_performer = ?"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                
                val filasBorradas = statement.executeUpdate()
                filasBorradas > 0 // Devuelve true si la eliminación fue exitosa
            }
        } catch (e: Exception) {
            println("Error al eliminar el artista con ID $id: ${e.message}")
            false
        }
    }
}