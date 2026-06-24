package com.example.boxvideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.boxvideo.ui.navigation.RootNavigation
import com.example.boxvideo.ui.theme.BoxVideoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable

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
                    else -> {
                        RootNavigation(appState)
                    }
                }
            }
        }
    }
}








@Serializable
data class User(
    val login: String = "",
    val email: String = "",
    val username: String = "",
    val role: String = "",
)









