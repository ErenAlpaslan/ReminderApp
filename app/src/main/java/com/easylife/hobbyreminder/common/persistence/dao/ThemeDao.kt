package com.easylife.hobbyreminder.common.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.easylife.hobbyreminder.entity.ThemeEntity

@Dao
interface ThemeDao {

    @Insert
    suspend fun insertTheme(theme: ThemeEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertAllTheme(themes: List<ThemeEntity>)

    @Query("SELECT * FROM Themes")
    suspend fun getAllThemes(): List<ThemeEntity>

    @Query("SELECT * FROM Themes where id == :id")
    suspend fun getTheme(id: Int): ThemeEntity

}