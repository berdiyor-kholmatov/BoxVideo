package com.example.boxvideo.di

import android.content.Context
import com.example.boxvideo.data.local.mockvideo.MockVideoSourceImpl
import com.example.boxvideo.data.local.mockvideo.VideoSource
import com.example.boxvideo.data.remote.NetworkClient
import com.example.boxvideo.data.remote.video.AdminRemoteVideoSource
import com.example.boxvideo.data.remote.video.RemoteVideoSource
import com.example.boxvideo.data.remote.video.VideoSourceImpl
//import com.chuckerteam.chucker.api.ChuckerInterceptor
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
class RemoteDataSourceModule {
    @Provides
    fun provideMockVideoSource(
        mockVideoSource: MockVideoSourceImpl,
    ): VideoSource {

        return mockVideoSource
    }

    @Provides
    fun provideVideoSource(
        remoteVideoSource: VideoSourceImpl
    ): RemoteVideoSource {
        return remoteVideoSource
    }

    @Provides
    fun provideAdminVideoSource(
        adminRemoteVideoSource: VideoSourceImpl
    ): AdminRemoteVideoSource {
        return adminRemoteVideoSource
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