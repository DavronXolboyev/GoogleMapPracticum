package uz.doxmobile.google_practicum.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.doxmobile.google_practicum.db.convertor.DoubleConvertor
import uz.doxmobile.google_practicum.db.convertor.LocalDateConvertor

/**
 *   Davron
 *   09.08.2023  |  среда  |  12:16
 */
@Database(entities = [LocationEntity::class], version = 2)
@TypeConverters(LocalDateConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val locationDao: LocationDao

    companion object {
        const val DB_NAME = "location.db"
    }
}