package uz.doxmobile.google_practicum.service

import android.location.Location
import kotlinx.coroutines.flow.Flow

/**
 *   Davron
 *   10.08.2023  |  четверг  |  10:18
 */

interface LocationClient {
    fun getLocationUpdates(interval : Long) : Flow<Location>

    class LocationException(message: String) : Exception(message)
}