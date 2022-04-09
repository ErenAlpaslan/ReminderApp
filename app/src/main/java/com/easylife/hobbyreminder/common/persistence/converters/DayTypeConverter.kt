package com.easylife.hobbyreminder.common.persistence.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.easylife.hobbyreminder.entity.Day
import com.easylife.hobbyreminder.entity.Time
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.reflect.Type

class DayTypeConverter: KoinComponent {

    private val gson: Gson by inject()

    @TypeConverter
    fun toString(day: Day?): String? {
        if (day == null) {
            return null
        }
        val type: Type = object : TypeToken<Day?>(){}.type
        return gson.toJson(day, type)
    }

    @TypeConverter
    fun fromString(day: String?): Day? {
        if (day == null) {
            return null
        }
        val type: Type = object : TypeToken<Day?>(){}.type
        return gson.fromJson(day, type)
    }
}