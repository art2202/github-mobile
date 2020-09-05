package com.example.desafioradix.database

import androidx.room.TypeConverter
import com.example.desafioradix.Constantes
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String): Date? {
        try {
            val sdf = SimpleDateFormat(Constantes.FORMATO_DATA, Locale.US)
            return sdf.parse(value)
        }
        catch (e: ParseException) {
            throw RuntimeException(e)
        }
    }


    @TypeConverter
    fun dateToTimestamp(date: Date): String {
        val sdf = SimpleDateFormat(Constantes.FORMATO_DATA, Locale.US)
        return sdf.format(date)
    }
}
