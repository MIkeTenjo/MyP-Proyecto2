import java.sql.Connection

class InGroupDaoSQLite(private val connection: Connection) : InGroupDao {

    override fun insertRelation(inGroup: InGroup): Boolean {
        val sql = "INSERT INTO in_group (id_person, id_group) VALUES (?, ?)"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, inGroup.id_person)
                statement.setInt(2, inGroup.id_group)
                
                statement.executeUpdate() > 0
            }
        } catch (e: Exception) {
            println("Error al insertar la relación artista-grupo: ${e.message}")
            false
        }
    }

    override fun getMembersOfGroup(idGroup: Int): List<Person> {
        // Hacemos JOIN entre la tabla puente y la tabla de personas
        val sql = """
            SELECT p.id_person, p.stage_name, p.real_name, p.birth_date, p.death_date
            FROM persons p
            JOIN in_group ig ON p.id_person = ig.id_person
            WHERE ig.id_group = ?
        """.trimIndent()
        
        val members = mutableListOf<Person>()
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, idGroup)
                statement.executeQuery().use { rs ->
                    while (rs.next()) {
                        members.add(
                            Person(
                                id_person = rs.getInt("id_person"),
                                stage_name = rs.getString("stage_name"),
                                real_name = rs.getString("real_name"),
                                birth_date = rs.getString("birth_date"),
                                death_date = rs.getString("death_date") // Maneja el null automáticamente
                            )
                        )
                    }
                }
            }
            members
        } catch (e: Exception) {
            println("Error al obtener los miembros del grupo: ${e.message}")
            emptyList()
        }
    }

    override fun getGroupsOfPerson(idPerson: Int): List<Group> {
        // Aquí necesitamos triple JOIN: 
        // in_group -> groups -> performers (para sacar el nombre del grupo)
        val sql = """
            SELECT g.id_group, perf.name, g.start_date, g.end_date 
            FROM groups g 
            JOIN in_group ig ON g.id_group = ig.id_group 
            JOIN performers perf ON g.id_performer = perf.id_performer 
            WHERE ig.id_person = ?
        """.trimIndent()
        
        val groups = mutableListOf<Group>()
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, idPerson)
                statement.executeQuery().use { rs ->
                    while (rs.next()) {
                        groups.add(
                            Group(
                                id_group = rs.getInt("id_group"),
                                name = rs.getString("name"),
                                start_date = rs.getString("start_date"),
                                end_date = rs.getString("end_date")
                            )
                        )
                    }
                }
            }
            groups
        } catch (e: Exception) {
            println("Error al obtener los grupos del artista: ${e.message}")
            emptyList()
        }
    }

    // Nota: Mantuve el nombre de la función tal cual está en tu interfaz (deletRelation)
    override fun deletRelation(idPerson: Int, idGroup: Int): Boolean {
        // Borramos usando ambas llaves para identificar la relación exacta
        val sql = "DELETE FROM in_group WHERE id_person = ? AND id_group = ?"
        
        return try {
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, idPerson)
                statement.setInt(2, idGroup)
                
                statement.executeUpdate() > 0
            }
        } catch (e: Exception) {
            println("Error al eliminar la relación: ${e.message}")
            false
        }
    }
}