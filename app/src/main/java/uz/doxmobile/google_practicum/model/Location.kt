package uz.doxmobile.google_practicum.model

import org.threeten.bp.LocalDateTime

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:25
 */

data class Location(
    val id: Long = -1,
    val latitude: Double,
    val longitude: Double,
)