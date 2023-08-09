package com.redvelvet.local.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val IsLoggedByAccount = booleanPreferencesKey("is_logged_by_account")
    val IsLoggedByGuest = booleanPreferencesKey("is_logged_by_guest")
    val IsFirstTimeUsingApp = booleanPreferencesKey("is_first_time_using_app")
    val Token = stringPreferencesKey("token")
    val SessionId = stringPreferencesKey("session_id")
    val GuestSessionId = stringPreferencesKey("guest_session_id")
    val GuestSessionExpDate = stringPreferencesKey("guest_exp_date_session")
}