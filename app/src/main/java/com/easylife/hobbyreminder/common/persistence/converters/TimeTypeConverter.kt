package com.easylife.hobbyreminder.common.persistence.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.easylife.hobbyreminder.entity.Time
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.reflect.Type

class TimeTypeConverter: KoinComponent {
    private val gson: Gson by inject()

    @TypeConverter
    fun toString(time: Time?): String? {
        if (time == null) {
            return null
        }
        val type: Type = object : TypeToken<Time?>(){}.type
        return gson.toJson(time, type)
    }

    @TypeConverter
    fun fromString(time: String?): Time? {
        if (time == null) {
            return null
        }
        val type: Type = object : TypeToken<Time?>(){}.type
        return gson.fromJson(time, type)
    }

}