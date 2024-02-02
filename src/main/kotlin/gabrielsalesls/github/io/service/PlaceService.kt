package gabrielsalesls.github.io.service

import gabrielsalesls.github.io.data.dto.PlaceDto
import gabrielsalesls.github.io.data.entity.Place
import gabrielsalesls.github.io.repository.PlaceRepository

interface PlaceService {
    suspend fun findAll(): List<Place>
    suspend fun findPlaceById(placeId: Int): Place?
    suspend fun findPlaceByName(placeName: String): List<Place>
    suspend fun savePlace(placeDto: PlaceDto): Place
    suspend fun editPlace(placeId: Int, placeDto: PlaceDto): Place

}

class PlaceServiceImpl(private val placeRepository: PlaceRepository): PlaceService {

    override suspend fun findAll(): List<Place> {
        return placeRepository.findAll()
    }

    override suspend fun findPlaceById(placeId: Int): Place? {
        return placeRepository.findById(placeId)
    }

    override suspend fun findPlaceByName(placeName: String): List<Place> {
        return placeRepository.findByName(placeName)

    }

    override suspend fun savePlace(placeDto: PlaceDto): Place {

        val place = placeRepository.addNewPlace(placeDto)
        return place ?: throw Exception("Error to save place in database")

    }

    override suspend fun editPlace(placeId: Int, placeDto: PlaceDto): Place {

        return placeRepository.editPlace(placeId, placeDto)

    }

}