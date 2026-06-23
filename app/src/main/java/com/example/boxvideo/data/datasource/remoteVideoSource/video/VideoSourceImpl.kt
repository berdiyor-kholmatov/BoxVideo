package com.example.boxvideo.data.datasource.remoteVideoSource.video

import com.example.boxvideo.data.datasource.remoteVideoSource.video.model.VideoDto
import com.example.boxvideo.data.datasource.remoteVideoSource.video.model.VideoQualityDto
import com.example.boxvideo.data.datasource.remoteVideoSource.video.model.VideoSourceDto
import javax.inject.Inject

class VideoSourceImpl @Inject constructor() : AdminRemoteVideoSource {
    override suspend fun getVideos(): List<VideoDto> {

        val videoFilesDto: List<VideoDto> = listOf(
            VideoDto(
                id = 1,
                title = "Leo",
                description = "Leo ismli kaltakesak va Skvirtl ismli " +
                        "toshbaqa maktab terrariumida yashaydi. Leo 74 " +
                        "yoshga kirganini bilib, hayoti behuda o‘tganidan " +
                        "afsuslanadi. Bir kuni bolalar sinf jonivorlarini " +
                        "uyiga olib ketadigan bo‘lishadi, Leo esa qochmoqchi " +
                        "paytda gapira olishini oshkor qilib qo‘yadi. Shundan " +
                        "keyin u sirini saqlash evaziga bolalarga hayotiy " +
                        "maslahatlar bera boshlaydi.\n" +
                        "\n" +
                        "Лео 2023  Ящерица Лео и черепаха Сквиртл живут в " +
                        "террариуме класса начальной школы. Они десятилетиями " +
                        "наблюдали взросление школьников, неплохо разбираются в " +
                        "детской психологии и даже умеют говорить, но скрывают это " +
                        "от людей. Однажды Лео слышит, что ящерицы его вида живут " +
                        "до 75 лет, а когда вычисляет, что ему уже 74, то в ужасе " +
                        "осознаёт, что жизнь прошла, а он так ничего толком и не видел. " +
                        "Как раз в это время замещающая учительница решает научить детей " +
                        "ответственности и поручает брать питомцев класса на " +
                        "выходные домой. Лео пытается воспользоваться этой " +
                        "возможностью и сбежать на волю, но в процессе " +
                        "пробалтывается и теперь вынужден раздавать пятиклассникам" +
                        " жизненные советы, чтобы они никому не рассказали о его тайне.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/ed/8bd5c566809505f3363b226e6bee64.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/33/kinolar/Leo%202023%20480p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/37/kinolar/Leo%202023%20720p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/38/kinolar/Leo%202023%201080p%20(asilmedia.net).mp4"
                    )
                )
            )
        )


        return videoFilesDto
    }

    override suspend fun getVideoById(id: Int): VideoDto? {
        TODO("Not yet implemented")
    }

    override suspend fun searchVideo(query: String): List<VideoDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addVideo(video: VideoDto): Int {
        TODO("Not yet implemented")
    }

    override suspend fun updateVideo(
        id: Int,
        video: VideoDto
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVideo(id: Int) {
        TODO("Not yet implemented")
    }
}