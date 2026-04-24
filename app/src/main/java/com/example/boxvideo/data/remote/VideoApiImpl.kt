package com.example.boxvideo.data.remote

import javax.inject.Inject

class VideoApiImpl @Inject constructor() : VideoApi {
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
            ),
            VideoDto(
                id = 2,
                title = "Yovvoyi robot",
                description = "Rozzum 7134 roboti (qisqartirilgan \"roz\") " +
                        "kimsasiz orolda kema halokatiga uchraydi. U mahalliy" +
                        " sharoitga moslashishga harakat qiladi va u erda " +
                        "yashovchi hayvonlar bilan do'stlashadi.\n" +
                        "\n" +
                        "Робот ROZZUM 7134 (сокращенно «Роз») терпит " +
                        "кораблекрушение на безлюдном острове. Он пытается " +
                        "приспособиться к местным условиям и заводит дружбу " +
                        "с обитающими там животными.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/a7/ff6ea176ac9e9ec4680123e7bb3362.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/35/kinolar/Yovvoyi%20robot%202024%20480p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/36/kinolar/Yovvoyi%20robot%202024%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/37/kinolar/Yovvoyi%20robot%202024%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 3,
                title = "Mune: Oy qo'riqchisi",
                description = "Хранитель Луны 2014 На диковинной планете, " +
                        "где смена дня и ночи осуществляется вручную, юного" +
                        " фавна Мьюна неожиданно избирают хранителем Луны. " +
                        "Но к своему ужасу, он почти сразу теряет ее! " +
                        "Воспользовавшись этим, царь подземного мира Некрос " +
                        "крадет и гасит Солнце. Теперь, чтобы спасти планету, " +
                        "Мьюн вместе с отважной девочкой из воска Глим и " +
                        "заносчивым Сохоном, хранителем Солнца, должны отправиться " +
                        "в удивительное и опасное путешествие.\n" +
                        "\n" +
                        "\n" +
                        "G‘aroyib bir sayyorada kun va tun almashinuvi qo‘lda " +
                        "boshqariladi. Yosh favn Myun kutilmaganda Oy qo‘riqchisi " +
                        "etib saylanadi. Ammo dahshatlisi shundaki, u Oyni deyarli " +
                        "darrov yo‘qotib qo‘yadi.Shundan foydalangan yerosti dunyosi " +
                        "qiroli Nekros Quyoshni o‘g‘irlab, uni so‘ndiradi. Endi " +
                        "sayyorani qutqarish uchun Myun jasur mum qiz Glim va Quyosh " +
                        "qo‘riqchisi, o‘ziga bino qo‘ygan Soxon bilan birga hayratlanarli " +
                        "va xavfli sayohatga otlanishi kerak.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/45/c98f867bb8800f5558e38ba277ace9.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/29/kinolar/Mune%20Oy%20qo'riqchisi%20480p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/33/kinolar/Mune%20Oy%20qo'riqchisi%20720p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/30/kinolar/Mune%20Oy%20qo'riqchisi%201080p%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 4,
                title = "Kung Fu Panda 4",
                description = "Afsonaviy Ajdar jangchisi, uning sodiq do'stlari va ustozi sarguzashtlarining davomi.\n" +
                        "Продолжение приключений легендарного воина По, его верных друзей и наставника.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/17/2a4882fe395526fc46eed102817ab3.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/16/kinolar/Kung%20Fu%20Panda%204%20480p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/28/kinolar/Kung%20Fu%20Panda%204%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/16/kinolar/Kung%20Fu%20Panda%204%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
        )

        return videoFilesDto
    }
}