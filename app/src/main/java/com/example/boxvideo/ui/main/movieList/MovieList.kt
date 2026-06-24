package com.example.boxvideo.ui.main.movieList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import okhttp3.OkHttpClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(state: MovieState, onEvent: (MovieEvents) -> Unit, onClick: (Int) -> Unit){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "BoxVideo",
                        fontWeight =  FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Search,
                            contentDescription = "Add new library"
                        )
                    }



                    IconButton(
                        onClick = { onEvent(MovieEvents.ProfilePressed) },
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Sharp.AccountCircle,
                            contentDescription = "Add new library"
                        )
                    }
                    // Само выпадающее окошко
                    DropdownMenu(
                        expanded = state.isProfileInfoExpanded,
                        onDismissRequest = { onEvent(MovieEvents.ProfilePressed) } // Закроется при клике мимо
                    ) {
                        // Квадратное окошко с данными пользователя
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
//                                .width(200.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            // Имя

                            Text(
                                text = createLabeledString("Name", state.user.username),
                                style = MaterialTheme.typography.bodySmall
                            )
                            // Email
                            Text(
                                text = createLabeledString("Email", state.user.email),
                                style = MaterialTheme.typography.bodySmall
                            )

                            Text(
                                text = createLabeledString("Login", state.user.login),
                                style = MaterialTheme.typography.bodySmall,
                            )

                            Text(
                                text = createLabeledString("Role", state.user.role),
                                style = MaterialTheme.typography.bodySmall,
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            HorizontalDivider() // Тонкая линия-разделитель
                            Spacer(modifier = Modifier.height(8.dp))

                            // Кнопка Log Out
                            Button(
                                onClick = {
                                    onEvent(MovieEvents.ProfilePressed) // Закрываем меню
                                    onEvent(MovieEvents.LogOut)        // Вызываем колбэк выхода
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.error
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Log Out")
                            }
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }

    ) { contentPadding ->

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
                .padding(contentPadding)
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
}


fun createLabeledString(
    header: String,
    value: String
): AnnotatedString {
    return  buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            ),
        ){
            append("$header: ")
        }

        withStyle(
            style = SpanStyle(
                color = Color.Gray
            ),
        ) {
            append(value)
        }
    }
}


@Composable
fun ProfileAvatarWithMenu(
    userName: String,
    userEmail: String,
    onLogoutClick: () -> Unit
) {
    // Состояние: открыто меню или закрыто
    var isMenuExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        // Иконка профиля, на которую нажимаем
        IconButton(onClick = { isMenuExpanded = true }) {
            Icon(
                imageVector = Icons.Sharp.AccountCircle,
                contentDescription = "Профиль",
                modifier = Modifier.size(32.dp)
            )
        }

        // Само выпадающее окошко
        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false } // Закроется при клике мимо
        ) {
            // Квадратное окошко с данными пользователя
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Имя
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleMedium
                )
                // Email
                Text(
                    text = userEmail,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider() // Тонкая линия-разделитель
                Spacer(modifier = Modifier.height(8.dp))

                // Кнопка Log Out
                Button(
                    onClick = {
                        isMenuExpanded = false // Закрываем меню
                        onLogoutClick()        // Вызываем колбэк выхода
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Log Out")
                }
            }
        }
    }
}

