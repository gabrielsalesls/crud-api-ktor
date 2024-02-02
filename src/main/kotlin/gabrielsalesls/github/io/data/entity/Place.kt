package gabrielsalesls.github.io.data.entity

import gabrielsalesls.github.io.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Place(
    var id: Int,
    var name: String,
    var city: String,
    var state: String,

    @Serializable(with = LocalDateTimeSerializer::class)
    var createdAt: LocalDateTime,

    @Serializable(with = LocalDateTimeSerializer::class)
    var updatedAt: LocalDateTime?
)

