package com.example.boxvideo.data.db

import androidx.room.TypeConverter
import com.example.boxvideo.domain.model.VideoSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VideoSourceConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromVideoSourceList(value: List<VideoSource>): String {
        return gson.toJson(value)
    }
    @TypeConverter
    fun toVideoSourceList(value: String): List<VideoSource> {
        val listType = object : TypeToken<List<VideoSource>>() {}.type
        return gson.fromJson(value, listType)
    }
}
