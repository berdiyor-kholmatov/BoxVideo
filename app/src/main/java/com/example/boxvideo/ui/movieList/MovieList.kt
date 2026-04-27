package com.example.boxvideo.ui.movieList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import okhttp3.OkHttpClient

@Composable
fun MovieList(state: MovieState, onEvent: (MovieEvents) -> Unit, onClick: (Int) -> Unit){

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

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        content = {
            items(state.videoPreviews) { video ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .clickable(
                            onClick = {
                                onClick(video.id)
                            }
                        )

                ){
                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(6.dp)
                   ) {

                       AsyncImage(
                           model = ImageRequest.Builder(context)
                               .data(video.thumbnailUrl)
                               .build(),
                           imageLoader = imageLoader,
                           contentDescription = null,
                           modifier = Modifier
                               .fillMaxWidth()
                               .height(250.dp)
                               .aspectRatio(19/29f)
                               .clip(RoundedCornerShape(6.dp)),
                           contentScale = ContentScale.Crop,
                           onError = {
                               println("ERROR OF IMAGE ASYNC IMAGE: ${it.result.throwable}")
                           }
                       )
                       Text(
                           text = video.title,
                           fontSize = 16.sp,
                           fontWeight = FontWeight.Bold,
                           color = Color.White
                       )
                   }
                }
            }
        }
    )

}