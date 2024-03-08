package com.sixth.space.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import java.util.Date

/**
 * @author: Frankie
 * @Date: 2024/3/9
 * @Description:
 */
@Database(entities = [VideoInfo::class], version = 1)
@TypeConverters(ConsumptionConverters::class,DateConverter::class)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun VideoDao(): VideoDao
}
class ConsumptionConverters {
    @TypeConverter
    fun fromConsumption(consumption: VideoInfo.Consumption): String {
        val gson = Gson()
        return gson.toJson(consumption)
    }

    @TypeConverter
    fun toConsumption(consumptionString: String): VideoInfo.Consumption {
        val gson = Gson()
        return gson.fromJson(consumptionString, VideoInfo.Consumption::class.java)
    }
}

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}