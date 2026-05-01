package com.example.boxvideo.ui.movieDetail

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boxvideo.PlayerActivity
import okhttp3.OkHttpClient

@Composable
fun Detail (
    state: DetailState,
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
                   .height(350.dp)
                   .aspectRatio(19/29f)
                   .clip(RoundedCornerShape(6.dp)),
               contentScale = ContentScale.Crop,
               onError = {
                   println("ERROR OF IMAGE ASYNC IMAGE: ${it.result.throwable}")
               }
           )
           Text(
               text = state.video?.title ?: "",
               fontSize = 24.sp,
               fontWeight = FontWeight.Bold,
               color = Color.White
           )
           Text(
               text = state.video?.description ?: "",
               fontSize = 16.sp,
               fontWeight = FontWeight.Bold,
               color = Color.White
           )

           Button(
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


