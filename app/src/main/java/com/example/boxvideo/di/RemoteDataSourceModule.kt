package com.example.boxvideo.di

import android.content.Context
//import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.boxvideo.network.client.NetworkClient
import com.example.boxvideo.data.datasource.localVideoSource.VideoSource
import com.example.boxvideo.data.datasource.localVideoSource.MockVideoSourceImpl
import com.example.boxvideo.data.datasource.remoteVideoSource.video.AdminRemoteVideoSource
import com.example.boxvideo.data.datasource.remoteVideoSource.video.RemoteVideoSource
import com.example.boxvideo.data.datasource.remoteVideoSource.video.VideoSourceImpl
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