package uz.doxmobile.google_practicum.db.convertor

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:31
 */

class LocalDateConvertor {
    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }

    @TypeConverter
    fun toLocalDateTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }
}