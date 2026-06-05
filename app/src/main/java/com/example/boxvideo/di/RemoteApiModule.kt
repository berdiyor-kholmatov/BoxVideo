package com.example.boxvideo.di

import android.content.Context
//import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.boxvideo.network.client.NetworkClient
import com.example.boxvideo.network.remote.VideoApi
import com.example.boxvideo.network.remote.VideoApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class RemoteApiModule {
    @Provides
    fun provideVideoApi(): VideoApi {
        return VideoApiImpl()
    }

    @Provides
    fun provideNetworkClient(@ApplicationContext context: Context) : NetworkClient
    {
        val client = HttpClient(OkHttp)
        {
            install(ContentNegotiation)
            {
                json(
                    Json { ignoreUnknownKeys = true}
                )
            }
//            engine {
//                addInterceptor(ChuckerInterceptor(context))
//            }
        }
        return NetworkClient(client)
    }


}