package uz.doxmobile.google_practicum.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 *   Davron
 *   09.08.2023  |  среда  |  12:16
 */

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM location")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Query("SELECT * FROM location ORDER BY id DESC LIMIT 1")
    fun getLastLocation(): Flow<LocationEntity?>
}