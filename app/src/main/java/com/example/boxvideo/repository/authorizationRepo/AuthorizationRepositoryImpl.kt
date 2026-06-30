package com.example.boxvideo.repository.authorizationRepo

import android.util.Log
import com.example.boxvideo.AppState
import com.example.boxvideo.BuildConfig
import com.example.boxvideo.User
import com.example.boxvideo.data.cache.datastore.authstate.AuthorizationStorage
import com.example.boxvideo.data.cache.datastore.token.TokenStorage
import com.example.boxvideo.data.network.NetworkClient
import com.example.boxvideo.data.remoteDataSource.authorization.models.register.UserRegisterRequest
import com.example.boxvideo.data.remoteDataSource.authorization.models.register.UserRegisterResponse
import com.example.boxvideo.data.remoteDataSource.authorization.models.user.UserLoginRequest
import com.example.boxvideo.data.remoteDataSource.authorization.models.user.UserLoginResponse
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

    override suspend fun register(
        login: String,
        email: String,
        password: String
    ): Boolean {
        try {
            val response = networkClient.post(
                url = "${BuildConfig.BASE_URL}/auth/register",
                headers = emptyMap(),
                body = UserRegisterRequest(login = login, email = email, password = password),
                responseType = UserRegisterResponse::class
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

    override suspend fun getUserInfo(): User? {
        val token = tokenStorage.getToken() ?: return null
        try {
            val user = networkClient.get(
                url = "${BuildConfig.BASE_URL}/auth/me",
                headers = mapOf("Authorization" to "Bearer $token"),
                responseType = User::class
            )
            Log.d("USER_AUTHORIZATION_ME", "User: $user")
            return user
        } catch (e: Exception) {
            Log.e("USER_AUTHORIZATION_ME", "Error: ${e.message}", e)
            return null
        }
    }

    override suspend fun setAuthState(state: AppState) {
        authorizationStorage.setAuthState(state)
    }

    override fun observeAuthState(): Flow<AppState> {
        return authorizationStorage.observeAuthState()
    }
}