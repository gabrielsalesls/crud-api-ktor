package gabrielsalesls.github.io.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Places: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val city = varchar("city", 128)
    val state = varchar("state", 128)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}