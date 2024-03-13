package com.micudasoftware.data.user.datasource

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Local data source for user data.
 *
 * @property sharedPreferences The [SharedPreferences] to store auth token.
 */
class UserLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    /**
     * Function to save user's auth token.
     *
     * @param token The authorization token.
     */
    fun saveAuthorizationToken(token: String) {
        sharedPreferences
            .edit()
            .putString(AUTH_TOKEN_KEY, token)
            .apply()
    }

    /**
     * Function to get user's authorization token.
     *
     * @return The user's authorization token if exists, otherwise null.
     */
    fun getAuthorizationToken(): String? =
        sharedPreferences.getString(AUTH_TOKEN_KEY, null)

    private companion object {
        private const val AUTH_TOKEN_KEY = "auth_token"
    }
}
