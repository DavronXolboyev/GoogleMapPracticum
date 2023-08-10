package uz.doxmobile.google_practicum.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.doxmobile.google_practicum.repository.LocationRepository
import uz.doxmobile.google_practicum.repository.impl.LocationRepositoryImpl

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:35
 */

@InstallIn(SingletonComponent::class)
@Module
abstract class BindModule {
    @Binds
    abstract fun bindLocationRepository(locationRepositoryImpl: LocationRepositoryImpl) : LocationRepository
}