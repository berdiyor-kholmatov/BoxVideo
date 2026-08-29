package com.example.boxvideo.ui.main.movieDetail

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoQuality
import com.example.boxvideo.domain.model.VideoSource
import com.example.boxvideo.ui.common.ExpandableTextInlineMore
import com.example.boxvideo.ui.player.PlayerActivity
import okhttp3.OkHttpClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail (
    state: DetailState,
    onBack: () -> Unit,
    onEvent: (DetailEvents) -> Unit,
    onClick: (Int) -> Unit
) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .okHttpClient {
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", "Mozilla/5.0")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            }
            .build()
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "VideoDetails",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = { onBack() },
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = "Navigation back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier.fillMaxSize().padding(contentPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                item(){
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(state.video?.thumbnailUrl)
                            .build(),
                        imageLoader = imageLoader,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Gray)
                                .height(300.dp)
                            .aspectRatio(19/29f),
                        contentScale = ContentScale.Crop,
                        onError = {
                            println("ERROR OF IMAGE ASYNC IMAGE: ${it.result.throwable}")
                        }
                    )


                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = state.video?.title ?: "",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    ExpandableTextInlineMore(
                        text = state.video?.description ?: "",
                        minimizedMaxLines = 4,
                    )

//                    Text(
//                        text = state.video?.description ?: "",
//                        maxLines = 3,
//                        overflow = TextOverflow.Ellipsis,
//                        color = Color.White
//                    )


//                    Text(
//                        text = state.video?.description ?: "",
//                        fontSize = 16.sp,
////                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )

                    Button(
                        modifier = Modifier
                            .height(48.dp)
                            .width(88.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1DB954), // Поставьте нужный вам сочный цвет (например, синий)
                            contentColor = Color.White,         // Цвет текста/иконки внутри
                            disabledContainerColor = Color.Gray.copy(alpha = 0.4f), // Цвет, когда state.isLoading == true
                            disabledContentColor = Color.White.copy(alpha = 0.6f)
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("videoId", state.video?.id)
                            context.startActivity(intent)
                        }
                    ){
                        Text(text = "Play")
                    }

                    Spacer(modifier = Modifier.height(50.dp))
                }

            }
        }
    }
}



@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun DetailPreview(
){
    val video = VideoFile(
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
    )
    // 2. ИСПРАВЛЕНО: Обернули в MaterialTheme с принудительной тёмной палитрой,
    // чтобы не зависеть от настроек проекта прямо сейчас
    MaterialTheme(colorScheme = androidx.compose.material3.darkColorScheme()) {
        // 3. ИСПРАВЛЕНО: Дали корневой Surface, который закрасит весь белый экран студии в тёмный цвет
        androidx.compose.material3.Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Detail(
                state = DetailState(video = video),
                onBack = {},
                onEvent = { print("Event: $it") },
                onClick = {}
            )
        }
    }
}

