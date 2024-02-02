package gabrielsalesls.github.io.routes

import gabrielsalesls.github.io.data.dto.PlaceDto
import gabrielsalesls.github.io.service.PlaceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.placesRoutes(service: PlaceService) {
    route("/v1/place") {

        get {
            val places = service.findAll()
            call.respond(HttpStatusCode.OK, places)

        }

        post {
            call.application.environment.log.info("Saving new Place")

            val placeDto = call.receive<PlaceDto>()

            val response = service.savePlace(placeDto)

            call.respond(HttpStatusCode.Created, response)

        }

        get("/{id}") {

            val placeId = call.parameters["id"] ?: run {
                call.respond(HttpStatusCode.BadRequest, "Required params not found")
            }

            val place = service.findPlaceById(placeId.toString().toInt())

            place?.let {
                call.respond(HttpStatusCode.OK, place)
            } ?: call.respond(HttpStatusCode.NotFound)
        }

        get("/name/{name}") {

            val placeName = call.parameters["name"] ?: run {
                call.respond(HttpStatusCode.BadRequest, "Required params not found")
            }

            val places = service.findPlaceByName(placeName.toString())

            call.respond(HttpStatusCode.OK, places)
        }

        put("/{id}") {
            val placeId = call.parameters["id"] ?: run {
                call.respond(HttpStatusCode.BadRequest, "Required params not found")
            }

            val placeDto = call.receive<PlaceDto>()

            val place = service.editPlace(placeId.toString().toInt(), placeDto)

            call.respond(HttpStatusCode.OK, place)

        }
    }
}