package uz.doxmobile.google_practicum

import uz.doxmobile.google_practicum.db.LocationEntity
import uz.doxmobile.google_practicum.model.Location

/**
 *   Davron
 *   09.08.2023  |  среда  |  17:18
 */

fun Location.toEntity(): LocationEntity {
    return LocationEntity(
        lat = latitude,
        lng = longitude,
    )
}

fun LocationEntity.toModel(): Location {
    return Location(
        id = id,
        latitude = lat,
        longitude = lng,
    )
}

