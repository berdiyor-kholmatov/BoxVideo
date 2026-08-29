package com.example.boxvideo.data.local.datastore.authstate

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.boxvideo.AppState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthorizationStorageImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): AuthorizationStorage{

    companion object {
        val APP_STATE =
            stringPreferencesKey(
                "app_state"
            )
    }

    override suspend fun setAuthState(state: AppState) {
        dataStore.edit {
            it[APP_STATE] = when(state) {
                is AppState.Loading -> "Loading"
                is AppState.Authorized -> "Authorized"
                is AppState.Unauthorized -> "Unauthorized"
            }
        }
    }

    override fun observeAuthState(): Flow<AppState> {
        return dataStore.data.map{
            when(it[APP_STATE]) {
                "Authorized" -> AppState.Authorized
                "Unauthorized" -> AppState.Unauthorized
                else -> AppState.Loading
            }
        }
    }
}