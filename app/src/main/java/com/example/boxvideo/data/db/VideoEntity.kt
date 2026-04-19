package com.example.boxvideo.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boxvideo.domain.model.VideoSource

@Entity(tableName = "videos")
data class VideoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val sources: List<VideoSource>
)

