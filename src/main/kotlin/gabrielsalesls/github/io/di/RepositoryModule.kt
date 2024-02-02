package gabrielsalesls.github.io.di

import gabrielsalesls.github.io.repository.PlaceRepository
import gabrielsalesls.github.io.repository.PlaceRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<PlaceRepository> { PlaceRepositoryImpl() }
}