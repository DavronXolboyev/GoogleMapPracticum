package uz.doxmobile.google_practicum.repository

import kotlinx.coroutines.flow.Flow
import uz.doxmobile.google_practicum.model.Location

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:25
 */

interface LocationRepository {
    suspend fun insertLocation(location : Location)
    fun getAllLocations() : Flow<List<Location>>
    fun getLastLocation() : Flow<Location?>
}