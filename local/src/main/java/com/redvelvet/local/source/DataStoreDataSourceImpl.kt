package com.redvelvet.local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.redvelvet.local.util.PreferencesKeys
import com.redvelvet.repository.source.DataStoreDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStoreDataSource {
    // region user
    override suspend fun setIsLoggedByAccount(isLogged: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IsLoggedByAccount] = isLogged
        }
    }

    override suspend fun getIsLoggedByAccount(): Boolean {
        return dataStore.data.first()[PreferencesKeys.IsLoggedByAccount] ?: false
    }

    override suspend fun setIsLoggedByGuest(isLogged: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IsLoggedByGuest] = isLogged
        }
    }

    override suspend fun getIsLoggedByGuest(): Boolean {
        return dataStore.data.first()[PreferencesKeys.IsLoggedByGuest] ?: false
    }

    override suspend fun setIsFirstTimeUsingApp(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IsFirstTimeUsingApp] = isFirstTime
        }
    }

    override suspend fun getIsFirstTimeUsingApp(): Boolean {
        return dataStore.data.first()[PreferencesKeys.IsFirstTimeUsingApp] ?: true
    }
    //endregion

    // region auth
    override suspend fun setUserSessionId(id: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SessionId] = id
        }
    }

    override fun getUserSessionId(): String? {
        return runBlocking { dataStore.data.first()[PreferencesKeys.SessionId] }
    }

    override suspend fun setGuestSession(id: String, expDate: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.GuestSessionId] = id
            preferences[PreferencesKeys.GuestSessionExpDate] = expDate
        }
    }

    override fun getGuestSessionId(): String? {
        return runBlocking {
            dataStore.data.first()[PreferencesKeys.GuestSessionId]
        }
    }

    override fun getGuestSessionExpDate(): String? {
        return runBlocking {
            dataStore.data.first()[PreferencesKeys.GuestSessionExpDate]
        }
    }
    //endregion
}