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
    fun toString(days: List<Day>?): String? {
        if (days == null) {
            return null
        }
        val type: Type = object : TypeToken<List<Day>?>(){}.type
        return gson.toJson(days, type)
    }

    @TypeConverter
    fun fromString(days: String?): List<Day>? {
        if (days == null) {
            return null
        }
        val type: Type = object : TypeToken<List<Day>?>(){}.type
        return gson.fromJson(days, type)
    }
}