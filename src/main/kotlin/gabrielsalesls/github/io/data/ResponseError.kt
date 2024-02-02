package gabrielsalesls.github.io.data

import kotlinx.serialization.Serializable

@Serializable
data class ResponseError(
    val error: String
)