package uz.doxmobile.google_practicum.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.doxmobile.google_practicum.db.LocationDao
import uz.doxmobile.google_practicum.model.Location
import uz.doxmobile.google_practicum.repository.LocationRepository
import uz.doxmobile.google_practicum.toEntity
import uz.doxmobile.google_practicum.toModel
import javax.inject.Inject

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:34
 */

class LocationRepositoryImpl @Inject constructor(private val locationDao: LocationDao) :
    LocationRepository {

    override suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location.toEntity())
    }

    override fun getAllLocations(): Flow<List<Location>> {
        return locationDao.getAllLocations().map { list -> list.map { it.toModel() } }
    }

    override fun getLastLocation(): Flow<Location?> {
        return locationDao.getLastLocation().map { it?.toModel() }
    }
}