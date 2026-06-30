package com.example.boxvideo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import androidx.datastore.preferences.core.Preferences
import javax.inject.Singleton
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.boxvideo.data.cache.datastore.authstate.AuthorizationStorage
import com.example.boxvideo.data.cache.datastore.authstate.AuthorizationStorageImpl
import com.example.boxvideo.data.cache.datastore.token.TokenStorage
import com.example.boxvideo.data.cache.datastore.token.TokenStorageImpl


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences>{
        return PreferenceDataStoreFactory.create (
            produceFile = {
                context.preferencesDataStoreFile(
                    "app_preferences"
                )
            }
        )
    }

    @Provides
    @Singleton
    fun providesTokenStorage(impl: TokenStorageImpl): TokenStorage {
        return impl
    }

    @Provides
    @Singleton
    fun providesAuthorizationStorage(impl: AuthorizationStorageImpl): AuthorizationStorage {
        return impl
    }
}