package com.haura.uialarmapp.room

import android.graphics.Movie
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters

@Dao
interface AlarmDao {
    @Insert
    fun addAlarm (alarm : Alarm)

    @Query ("SELECT * FROM alarm")
    fun getAlarm():List<Alarm>

}