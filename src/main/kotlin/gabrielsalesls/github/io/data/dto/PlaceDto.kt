package gabrielsalesls.github.io.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    var name: String,
    var city: String,
    var state: String
)
