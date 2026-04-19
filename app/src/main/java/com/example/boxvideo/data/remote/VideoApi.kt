package com.example.boxvideo.data.remote

import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoQuality
import com.example.boxvideo.domain.model.VideoSource

class VideoApi {
    fun getVideos(): List<VideoFile> {

        val videoFiles: List<VideoFile> = listOf(
            VideoFile(
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
                    VideoSource(
                        quality = VideoQuality.P480,
                        url = "https://fayllar1.ru/33/kinolar/Leo%202023%20480p%20(asilmedia.net).mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P720,
                        url = "https://fayllar1.ru/37/kinolar/Leo%202023%20720p%20(asilmedia.net).mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P1080,
                        url = "https://fayllar1.ru/38/kinolar/Leo%202023%201080p%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoFile(
                id = 1,
                title = "Video 1",
                description = "Description for Video 1",
                thumbnailUrl = "https://example.com/thumbnail1.jpg",
                sources = listOf(
                    VideoSource(
                        quality = VideoQuality.P480,
                        url = "https://example.com/video1_480p.mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P720,
                        url = "https://example.com/video1_720p.mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P1080,
                        url = "https://example.com/video1_1080p.mp4"
                    )
                )
            ),
            VideoFile(
                id = 1,
                title = "Video 1",
                description = "Description for Video 1",
                thumbnailUrl = "https://example.com/thumbnail1.jpg",
                sources = listOf(
                    VideoSource(
                        quality = VideoQuality.P480,
                        url = "https://example.com/video1_480p.mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P720,
                        url = "https://example.com/video1_720p.mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P1080,
                        url = "https://example.com/video1_1080p.mp4"
                    )
                )
            ),
            VideoFile(
                id = 1,
                title = "Video 1",
                description = "Description for Video 1",
                thumbnailUrl = "https://example.com/thumbnail1.jpg",
                sources = listOf(
                    VideoSource(
                        quality = VideoQuality.P480,
                        url = "https://example.com/video1_480p.mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P720,
                        url = "https://example.com/video1_720p.mp4"
                    ),
                    VideoSource(
                        quality = VideoQuality.P1080,
                        url = "https://example.com/video1_1080p.mp4"
                    )
                )
            ),
        )


        return videoFiles
    }
}