package gabrielsalesls.github.io.repository

import gabrielsalesls.github.io.dao.DatabaseFactory.dbQuery
import gabrielsalesls.github.io.data.dto.PlaceDto
import gabrielsalesls.github.io.data.entity.Place
import gabrielsalesls.github.io.data.table.Places
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime

interface PlaceRepository {
    suspend fun addNewPlace(place: PlaceDto): Place?
    suspend fun findAll(): List<Place>
    suspend fun findById(placeId: Int): Place?
    suspend fun findByName(placeName: String): List<Place>
    suspend fun editPlace(placeId: Int, placeDto: PlaceDto): Place
}

class PlaceRepositoryImpl : PlaceRepository {

    override suspend fun addNewPlace(place: PlaceDto): Place? = dbQuery {
        val insertPlace = Places.insert {
            it[name] = place.name
            it[city] = place.city
            it[state] = place.state
            it[createdAt] = LocalDateTime.now()
        }
        return@dbQuery insertPlace
            .resultedValues?.singleOrNull()?.let(::resultRowToPlace)
    }

    override suspend fun findAll(): List<Place> = dbQuery {
        val allPlaces = Places.selectAll()
        return@dbQuery allPlaces.map { resultRow -> resultRowToPlace(resultRow) }

    }

    override suspend fun findById(placeId: Int): Place? = dbQuery {
        val place = Places.select { Places.id eq placeId }.singleOrNull()
        return@dbQuery place?.let { resultRowToPlace(it) }

    }

    override suspend fun findByName(placeName: String): List<Place> = dbQuery {
        val places = mutableListOf<Place>()

        Places.select { Places.name eq placeName }.forEach { resultRow ->
            places.add(resultRowToPlace(resultRow))
        }
        places
    }

    override suspend fun editPlace(placeId: Int, placeDto: PlaceDto): Place {
        dbQuery {
            Places.update({ Places.id eq placeId }) {
                it[name] = placeDto.name
                it[city] = placeDto.name
                it[state] = placeDto.name
                it[updatedAt] = LocalDateTime.now()
            }
        }

        return findById(placeId) ?: throw Exception("Error updating Place")
    }
}

private fun resultRowToPlace(row: ResultRow) = Place(
    id = row[Places.id],
    name = row[Places.name],
    city = row[Places.city],
    state = row[Places.state],
    createdAt = row[Places.createdAt],
    updatedAt = row[Places.updatedAt]
)