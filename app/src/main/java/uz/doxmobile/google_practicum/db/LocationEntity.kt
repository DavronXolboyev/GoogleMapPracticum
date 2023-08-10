package uz.doxmobile.google_practicum.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = -1,
    val lat: Double,
    val lng: Double,
)
