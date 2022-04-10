package com.easylife.hobbyreminder.common.persistence.converters

import androidx.room.TypeConverter
import com.easylife.hobbyreminder.entity.Day
import com.easylife.hobbyreminder.entity.ThemeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.reflect.Type

class ThemeTypeConverter: KoinComponent {

    private val gson: Gson by inject()

    @TypeConverter
    fun toString(theme: ThemeEntity?): String? {
        if (theme == null) {
            return null
        }
        val type: Type = object : TypeToken<ThemeEntity?>(){}.type
        return gson.toJson(theme, type)
    }

    @TypeConverter
    fun fromString(theme: String?): ThemeEntity? {
        if (theme == null) {
            return null
        }
        val type: Type = object : TypeToken<ThemeEntity?>(){}.type
        return gson.fromJson(theme, type)
    }


}