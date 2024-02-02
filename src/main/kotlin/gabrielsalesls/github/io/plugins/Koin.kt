package gabrielsalesls.github.io.plugins

import gabrielsalesls.github.io.di.repositoryModule
import gabrielsalesls.github.io.di.serviceModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(serviceModule)
        modules(repositoryModule)
    }
}
