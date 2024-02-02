package gabrielsalesls.github.io.plugins

import gabrielsalesls.github.io.data.ResponseError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, ResponseError(error = cause.message.toString()))
        }
    }
}
