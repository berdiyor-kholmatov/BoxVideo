package com.example.boxvideo.data.db

import androidx.room.Embedded
import androidx.room.Relation

data class VideoWithSources (
    @Embedded val video: VideoEntity,

    @Relation (
        parentColumn = "id",
        entityColumn = "videoId"
    )
    val sources: List<VideoSourceEntity>
)