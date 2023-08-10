package uz.doxmobile.google_practicum.db.convertor

import androidx.room.TypeConverter

/**
 *   Davron
 *   10.08.2023  |  четверг  |  13:50
 */

class DoubleConvertor {
    @TypeConverter
    fun fromDouble(value: Double): String {
        return value.toString()
    }

    @TypeConverter
    fun toDouble(value: String): Double {
        return value.toDouble()
    }
}