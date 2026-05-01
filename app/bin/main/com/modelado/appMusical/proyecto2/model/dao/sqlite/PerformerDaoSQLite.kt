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

    

    
}