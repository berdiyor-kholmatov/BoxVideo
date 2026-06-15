package com.example.boxvideo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
//import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.boxvideo.network.client.NetworkClient
import com.example.boxvideo.ui.navigation.NavigationRoot
import com.example.boxvideo.ui.navigation.Route
import com.example.boxvideo.ui.theme.BoxVideoTheme
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BoxVideoTheme {
                val activityViewModel = hiltViewModel<ActivityViewModel>()
                val appState by activityViewModel.appState.collectAsState()

                when(appState) {
                    AppState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }
                    AppState.Authorized -> {
                        NavigationRoot(Route.Home)
                    }
                    AppState.Unauthorized -> {
                        NavigationRoot(Route.Login())
                    }
                }
            }
        }
    }
}

@Serializable
data class User(
    val login: String,
    val email: String,
    val username: String,
    val role: String,
)









