package com.example.boxvideo.repository.networkRepo

import android.util.Log
import com.example.boxvideo.User
import com.example.boxvideo.data.datastore.TokenStorage
import com.example.boxvideo.network.client.NetworkClient
import com.example.boxvideo.network.models.user.UserLoginRequest
import com.example.boxvideo.network.models.user.UserLoginResponse
import kotlinx.serialization.Serializable
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage,
): NetworkRepository {
    override suspend fun checkAuth(): Boolean {
        val token = tokenStorage.getToken() ?: return false

        token.let {
            try {
                val user = networkClient.get(
                    url = "http://192.168.0.115:8080/auth/me", //115//106
                    headers = mapOf("Authorization" to "Bearer $token"),
                    responseType = User::class
                )
                Log.d("net", "User: $user")
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
                url = "http://192.168.0.115:8080/auth/login",
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
}
