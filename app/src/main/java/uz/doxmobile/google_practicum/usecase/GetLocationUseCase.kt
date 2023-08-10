package uz.doxmobile.google_practicum.usecase

import kotlinx.coroutines.flow.map
import uz.doxmobile.google_practicum.repository.LocationRepository
import javax.inject.Inject

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:44
 */

class GetLocationUseCase @Inject constructor(private val locationRepository: LocationRepository) {

    fun getAllLocations() = locationRepository.getAllLocations().map { list ->
        list.map { it }
    }

    fun getLastLocation() = locationRepository.getLastLocation().map { it }
}