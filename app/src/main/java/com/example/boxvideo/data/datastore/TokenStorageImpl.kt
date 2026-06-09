package com.example.boxvideo.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class TokenStorageImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenStorage {

    companion object {
        val TOKEN_KEY =
            stringPreferencesKey(
                "jwt_token"
            )
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.first()[TOKEN_KEY]
    }

    override suspend fun clearToken() {
        dataStore.edit {
            it.remove(TOKEN_KEY)
        }
    }
}