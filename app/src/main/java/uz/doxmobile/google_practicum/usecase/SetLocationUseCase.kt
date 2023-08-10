package uz.doxmobile.google_practicum.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.doxmobile.google_practicum.model.Location
import uz.doxmobile.google_practicum.repository.LocationRepository
import javax.inject.Inject

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:39
 */

class SetLocationUseCase @Inject constructor(private val locationRepository: LocationRepository) {

    suspend operator fun invoke(location: Location) = withContext(Dispatchers.IO) {
        locationRepository.insertLocation(location)
    }

}