package com.micudasoftware.data.user.datasource

import android.content.SharedPreferences
import com.micudasoftware.domain.user.model.UserCredentials

/**
 * Local data source for user data.
 *
 * @property sharedPreferences The [SharedPreferences] to store auth token.
 */
class UserLocalDataSource(
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


    /**
     * Function to save user's id.
     *
     * @param userId The user id.
     */
    fun saveUserId(userId: String) {
        sharedPreferences
            .edit()
            .putString(USER_ID_KEY, userId)
            .apply()
    }

    /**
     * Function to get user's id.
     *
     * @return The user's id if exists, otherwise null.
     */
    fun getUserId(): String? =
        sharedPreferences.getString(USER_ID_KEY, null)

    /**
     * Function to check if user is logged in.
     *
     * @return True if user is logged in, otherwise false.
     */
    fun isLoggedIn(): Boolean = sharedPreferences.contains(AUTH_TOKEN_KEY)

    /**
     * Function to save users credentials.
     *
     * @param credentials The user's credentials.
     */
    fun saveCredentials(credentials: UserCredentials) {
        sharedPreferences
            .edit()
            .putString(USER_LOGIN_KEY, credentials.login)
            .putString(USER_PASSWORD_KEY, credentials.password)
            .apply()
    }

    /**
     * Function to get saved user's credentials.
     *
     * @return The user's credentials if exist, otherwise false.
     */
    fun getCredentials(): UserCredentials? =
        sharedPreferences.getString(USER_LOGIN_KEY, null)?.let { login ->
            sharedPreferences.getString(USER_PASSWORD_KEY, null)?.let { password ->
                UserCredentials(login, password)
            }
        }

    private companion object {
        private const val AUTH_TOKEN_KEY = "auth_token"
        private const val USER_ID_KEY = "user_id"
        private const val USER_LOGIN_KEY = "user_login"
        private const val USER_PASSWORD_KEY = "user_password"
    }
}
