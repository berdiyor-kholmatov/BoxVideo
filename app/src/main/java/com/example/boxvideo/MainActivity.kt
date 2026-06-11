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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
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

        lifecycleScope.launch {
            try {
                val client = HttpClient(OkHttp) {
                    install(ContentNegotiation) {
                        json(Json { ignoreUnknownKeys = true })
                    }
                }
                val networkClient = NetworkClient(client)
                val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwL2hlbGxvIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODA4MCIsImxvZ2luIjoiU3RyaW5nIiwicm9sZSI6IlVTRVIiLCJleHAiOjE3ODA1Njk5MDN9.n3MW4UDiLAblfXwIK5b2A_gdrfQCxesPeOPwKh_jW54"

                val user = networkClient.get(
                    url = "http://192.168.0.103:8080/auth/me",
                    headers = mapOf("Authorization" to "Bearer $token"),
                    responseType = User::class
                )
                Log.d("net", "User: $user")
            } catch (e: Exception) {
                Log.e("net", "Error: ${e.message}", e)
            }
        }




        enableEdgeToEdge()
        setContent {
            BoxVideoTheme {
//                NavigationRoot()
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
                        NavigationRoot(Route.Login)
                    }
                }



//                val loginViewModel = hiltViewModel<LoginViewModel>()
//                val state by loginViewModel.state.collectAsState()
//                Login(state, loginViewModel::onEvent)
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









