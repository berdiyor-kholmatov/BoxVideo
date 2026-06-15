package com.example.boxvideo.repository.authorizationRepo

import android.util.Log
import com.example.boxvideo.AppState
import com.example.boxvideo.BuildConfig
import com.example.boxvideo.User
import com.example.boxvideo.data.datastore.authstate.AuthorizationStorage
import com.example.boxvideo.data.datastore.token.TokenStorage
import com.example.boxvideo.network.client.NetworkClient
import com.example.boxvideo.network.models.user.UserLoginRequest
import com.example.boxvideo.network.models.user.UserLoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage,
    private val authorizationStorage: AuthorizationStorage
): AuthorizationRepository {

    override suspend fun checkAuth(): Boolean {
        val token = tokenStorage.getToken() ?: return false

        token.let {
            try {
                val user = networkClient.get(
                    url = "${BuildConfig.BASE_URL}/auth/me", //115//106
                    headers = mapOf("Authorization" to "Bearer $token"),
                    responseType = User::class
                )
//                Log.d("net", "User: $user")
                return true
            } catch (e: Exception) {
                Log.e("net", "Error: ${e.message}", e)
                return false
            }
        }


    }

    override suspend fun login(login: String, password: String): Boolean {
        try {
            val response = networkClient.post(
                url = "${BuildConfig.BASE_URL}/auth/login",
                headers = emptyMap(),
                body = UserLoginRequest(login, password),
                responseType = UserLoginResponse::class
            )
            Log.d("net", "User: $response")
            tokenStorage.saveToken(response.token)
            return true
        } catch (e: Exception) {
            Log.e("net", "Error: ${e.message}", e)
            return false
        }
    }

    override suspend fun logOut(){
        tokenStorage.clearToken()
    }

    override suspend fun setAuthState(state: AppState) {
        authorizationStorage.setAuthState(state)
    }

    override fun observeAuthState(): Flow<AppState> {
        return authorizationStorage.observeAuthState()
    }
}
