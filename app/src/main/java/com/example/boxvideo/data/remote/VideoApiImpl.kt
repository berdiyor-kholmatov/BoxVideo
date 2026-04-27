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
            VideoDto(
                id = 5,
                title = "Super Mario 2: Galaktika",
                description = "Bouzer ustidan g‘alaba qozonib, Bruklinni qutqargach, Mario endi Vario va Bouzer Junior ittifoqiga duch keladi. Endi u do‘stlari va Yoshi bilan birga ularning dunyoni egallash rejasini to‘xtatishi kerak.\n" +
                        "\n" +
                        "Супер Марио: Галактическое кино 2026  После победы над Боузером и спасения Бруклина Марио сталкивается со злобным альянсом Варио и Боузера-младшего. Теперь вместе со своими друзьями и Йоши он должен помешать их планам по завоеванию мирового господства.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/44/e40e1aa928cc5fb2f19bfd634261c1.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/38/kinolar/Super%20Mario%202%20-%20Galaktika%20filmi%202026%20480p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/37/kinolar/Super%20Mario%202%20-%20Galaktika%20filmi%202026%20720p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/36/kinolar/Super%20Mario%202%20-%20Galaktika%20filmi%202026%201080p%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 6,
                title = "Super Mariolar",
                description = "Santexnik aka-uka Mario va Luidji er osti shahriga portal ochishadi, u erdan ular asirga olingan malika Shaftolini qutqarib, qasam ichgan dushmani Bouzerga qarshi kurashishlari kerak.\n" +
                        "Братья-водопроводчики Марио и Луиджи открывают портал в подземный город, из которого должны вызволить пленённую принцессу Пич и сразиться с заклятым врагом Боузером.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/fb/c40588b802ce9ce820501e3e88b1a5.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/24/kinolar/Super%20Mariolar%20480p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/23/kinolar/Super%20Mariolar%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/20/kinolar/Super%20Mariolar%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 7,
                title = "Yurak shiviri Anime",
                description = "14 yoshli Sidzuku Tokioda yashaydi. U bo‘sh vaqtining ko‘p qismini kitob o‘qish bilan o‘tkazadi va ba’zan she’rlar yozadi. Bir kuni u kutubxonada olgan kitoblarini undan oldin doim bir xil ism — Seidzi olganini payqab qoladi. Ma’lum bo‘lishicha, u parallel sinfdagi bola bo‘lib, dastlab Sidzukuga unchalik yoqmaydi. Vaqt o‘tishi bilan ular yaqinlashadi, Seidzining skripka ustasi bo‘lish orzusi esa qizni ham o‘z orzusini topishga undaydi. Shunda Sidzuku antiqa do‘kondagi mushuk haykalchasi — Baron haqida hikoya yozishni boshlaydi.\n" +
                        "\n" +
                        "Шёпот сердца 1995  14-летняя Сидзуку живет в Токио, все свободное время проводит за чтением и иногда пишет стихи. Однажды она замечает, что в библиотеке все книги до нее брал некий Сэидзи. Им оказывается мальчик из параллельного класса, и поначалу он Сидзуку совсем не нравится. Постепенно они сближаются, и стремление Сэидзи стать скрипичным мастером пробуждают в девушке желание тоже найти свою мечту. Она начинает сочинять историю о Бароне, статуэтке кота из антикварного магазина.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/f7/ec3ac97bbd1db26092ddf4f7367fb4.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/31/kinolar/Yurak%20shiviri%201995%20480p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/35/kinolar/Yurak%20shiviri%201995%20720p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/37/kinolar/Yurak%20shiviri%201995%201080p%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 8,
                title = "Oqim",
                description = "Yolg‘iz mushuk katta toshqin tufayli uyidan judo bo‘ladi va turli xil hayvonlar bilan birga bir kemada boshpana topadi. Ular yangi dunyoga moslashish va omon qolish yo‘lida birga harakat qilishlari kerak.\n" +
                        "\n" +
                        "Происходит глобальное наводнение, и черный кот вынужден спасаться в лодке вместе с другими животными.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/d3/7816bbce80fd706ae9a3ef2614e3d3.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/35/kinolar/Oqim%20480p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/37/kinolar/Oqim%20720p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/34/kinolar/Oqim%201080p%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 9,
                title = "Yonar qo'ng'izlar o'rmoni Yaponiya Anime",
                description = "Xotaru ismli qizcha arvohlar yashaydigan sehrli o'rmonda adashib qoladi. U Jin ismli niqobli bolani uchratadi, lekin uni bir tegish bilan eriydigan o'rmon ruhi deb hisoblaydi. Yangi do'sti yordamida Xotaru o'rmondan qochib ketdi, lekin u Jinni unuta olmaydi.\n" +
                        "\n" +
                        "Маленькая девочка Хотару заблудилась в заколдованном лесу, населенном привидениями. Она встречает мальчика в маске по имени Гин, но считает его лесным духом, который может растаять от одного прикосновения. С помощью нового друга Хотару выбралась из леса, но она не может забыть Гина.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/b2/316c20638268fd598372c34159e5fc.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/33/kinolar/Yonar%20qo'ng'izlar%20o'rmoni%202011%20480p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/36/kinolar/Yonar%20qo'ng'izlar%20o'rmoni%202011%20720p%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/34/kinolar/Yonar%20qo'ng'izlar%20o'rmoni%202011%201080p%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 10,
                title = "Dengiz qo'shig'i",
                description = "O'n yoshli Ben otasi Konor va singlisi Saoirse bilan orol mayoqchasida yashaydi. Qiz haligacha gapirmaydi, lekin ma'lum bo'lishicha, u shelkie, ya'ni olti yil oldin g'oyib bo'lgan onasi kabi muhr xalqiga tegishli. Qo'rqib ketgan Konor bolalarni buvisini ko'rish uchun shaharga jo'natadi, lekin Ben qaytishni orzu qiladi va dengiz Saoirseni chaqirishda davom etadi. Shunday qilib, ularning uyga sarguzashtli sayohati boshlanadi.\n" +
                        "\n" +
                        "\n" +
                        "Десятилетний Бен живет на островном маяке вместе с отцом Конором и маленькой сестренкой Сиршей. Девочка все никак не заговорит, но зато обнаруживается, что она шелки, то есть принадлежит к народу людей-тюленей, как и исчезнувшая шесть лет назад мать. Напуганный Конор отправляет детей в город к бабушке, но Бен жаждет вернуться, а Сиршу продолжает звать море. Так начинается их полное приключений путешествие домой.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/cf/5a71646039315c6e7ba8eccbb4a021.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/29/kinolar/Dengiz%20qo'shig'i%202014%20480p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/30/kinolar/Dengiz%20qo'shig'i%202014%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/31/kinolar/Dengiz%20qo'shig'i%202014%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 11,
                title = "Rango",
                description = "Rango - bu terrariumda yashaydigan va o'zini qahramon deb biladigan xameleyon, afsuski, o'zining jasurligini hech qanday tarzda namoyish eta olmaydi. Ammo u kutilmaganda Dirt shahrida o'zini topganida, u bunday imkoniyatga ega. Rango o'zini adolat uchun kurashchi deb e'lon qiladi va Yovvoyi G'arbda sherif kabi harakat qilishni boshlaydi. U hali bu qismlarda \"yaxshi yigit\" bo'lish eng hasadgo'y taqdir emasligini bilmaydi ...\n" +
                        "\n" +
                        "Ранго - хамелеон, который живет в террариуме и считает себя героем, которому, к сожалению, никак не удается проявить свое бесстрашие. Но когда он внезапно оказывается в городке Грязь, у него появляется такая возможность. Ранго провозглашает себя борцом за справедливость и начинает вести себя как шериф на Диком Западе. Он еще не знает, что быть «хорошим парнем» в этих краях не самая завидная участь...",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/0c/ba5a32adce968fda0a8358abcd58d1.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P480,
                        url = "https://fayllar1.ru/14/kinolar/Rango%202011%20480p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/14/kinolar/Rango%202011%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/14/kinolar/Rango%202011%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),

            VideoDto(
                id = 12,
                title = "Ov mavsumi 4: Qo'rqinchli ahmoq",
                description = "Deer Elliot, Dachshund Sosiska va ularning do'stlari fitna uyushtirib, bug 'ayig'ini dunyodagi hamma narsadan qo'rqishdan xalos qilishadi. Kechasi olovda Shafqatsiz bo'ri bo'ri haqidagi ertakni eshitib, qudratli grizzli xotirjamligini yo'qotdi va o'zini qo'rqoq sichqon kabi tuta boshladi. Shu sababli, kompaniya ajoyib ta'tilni o'tkazmoqda. Quruq ayiq qo'rquvini \"qo'rqitish\" uchun Elliot va Kolbasa qorong'i o'rmonga ekspeditsiyani jihozlab, ov mavsumini o'zining eng sirli aholisiga ochib berishdi ... Ammo o'tmishdagi barcha sarguzashtlar bu safar o'rmon jamoasini nima kutayotganiga nisbatan xira. Yozning birinchi multfilm komediyasida bosh aylantiruvchi ta'qiblar, kulgili hazillar va syujetning ajoyib burilishlari.\n" +
                        "\n" +
                        "Олень Эллиот, такса Сосиска и их друзья сговариваются отучить медведя Буга бояться всего на свете. Услышав у ночного костра байку про Свирепого Волка-Оборотня, могучий гризли совсем потерял покой и стал вести себя как трусливая мышь. Из-за этого у компании срываются классные каникулы. Чтобы «спугнуть» напрасные медвежьи страхи, Эллиот и Сосиска снаряжают экспедицию в Тёмный Лес, открывая на его самого загадочного обитателя... свой сезон охоты. Но все прошлые приключения меркнут по сравнению с тем, что ждёт лесную команду в этот раз. Головокружительные погони, уморительные шутки и крутые сюжетные повороты — в первой мульт-комедии лета.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/26/3a29d809966f44865b0c5288db7dd2.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%204%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%204%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 13,
                title = "Ov mavsumi 3",
                description = "Grizzli ayiq Bug va uning sodiq do'sti Eliot kiyiklarining aqldan ozgan sarguzashtlarining davomi. Bu safar Bug shaxsiy baxtni qidirishga kirishadi va o'zini sayohat qilayotgan tsirkda topadi, u erda u maftunkor rus ayig'i bilan uchrashadi.\n" +
                        "\n" +
                        "Продолжение сумасшедших приключений медведя-гризли Буга и его верного друга, оленя Элиота. На этот раз Буг пускается на поиски личного счастья и попадает... в бродячий цирк, где знакомится с очаровательной русской медведицей.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/99/d3dafd48c6573cd41198ff903d52da.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%203%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%203%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 14,
                title = "Ov mavsumi 2",
                description = "Boog va Eliot aqldan ozgan yangi sarguzashtlarga ega bo'ladilar! Eliot Jizelni yaxshi ko'radi, lekin janob Vinini buzilgan uy hayvonlari o'g'irlab ketganda, uni o'z egalariga qaytarib berishni niyat qilganida, u qurbongohga olib boradigan yo'ldan chiqib ketishi kerak. Boog, Eliot, McSkeizy, Buddy va boshqa o'rmon aholisi kolbasa o'xshash do'sti uchun keng ko'lamli qutqaruv operatsiyasini olib borishadi va tez orada dushman lagerida: uy hayvonlari dunyosida. Miniatyur pudle Fifi boshchiligidagi ichki sissiyalar janob Vinidan kurashsiz voz kechmoqchi emas.\n" +
                        "\n" +
                        "Буга и Элиота ждут новые безумные  приключения! Элиот без памяти влюбился в Жизель, но ему приходится сойти с дороги, ведущей к алтарю, когда Мистера Уини похищают избалованные домашние животные, которые намерены вернуть его хозяевам. Буг, Элиот, Макскизи, Бадди и другие обитатели леса разворачивают полномасштабную спасательную операцию ради своего похожего на сосиску друга, и вскоре оказываются в лагере врага: в мире домашних питомцев. Домашние неженки под предводительством карликового пуделя Фифи не собираются отдавать Мистера Уини без боя.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/2a/c7abdcb6b1249588e1a965ffdcad6b.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%202%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%202%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
            VideoDto(
                id = 15,
                title = "Ov mavsumi 1",
                description = "Odamlar orasida o'sib-ulg'aygan, o'rmonda ov mavsumi avjida. Ovchi ayiqqa qaramoqchi bo'lib, o'z o'quvchisini qidirayotgan bo'lsa, gritsli ayiq o'rmon kiyiklari bilan do'stlashadi.",
                thumbnailUrl = "https://asilmedia.org/uploads/mini/fullstory/77/512d0724910702bcfc1be82371a1e5.webp",
                sources = listOf(
                    VideoSourceDto(
                        quality = VideoQualityDto.P720,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%201%20720p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    ),
                    VideoSourceDto(
                        quality = VideoQualityDto.P1080,
                        url = "https://fayllar1.ru/13/Ov%20Mavsumi%201%201080p%20O'zbek%20tilida%20(asilmedia.net).mp4"
                    )
                )
            ),
        )

        return videoFilesDto
    }
}