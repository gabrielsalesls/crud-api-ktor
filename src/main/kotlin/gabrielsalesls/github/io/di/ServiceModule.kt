package gabrielsalesls.github.io.di

import gabrielsalesls.github.io.service.PlaceService
import gabrielsalesls.github.io.service.PlaceServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<PlaceService> { PlaceServiceImpl(get()) }
}