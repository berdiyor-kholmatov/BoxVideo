package com.example.boxvideo.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "video_source",
    foreignKeys = [
        ForeignKey(
            entity = VideoEntity::class,
            parentColumns = ["id"],
            childColumns = ["videoId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class VideoSourceEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val videoId: Int,
    val quality: VideoQualityDB,
    val url: String,
)
