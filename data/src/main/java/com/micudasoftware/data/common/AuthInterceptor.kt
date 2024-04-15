package com.micudasoftware.data.common

import com.micudasoftware.data.user.datasource.UserLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Authorization interceptor, that adds the authorization header to the request.
 *
 * @property userLocalDataSource The data source to get authorization token.
 * @see Interceptor
 */
class AuthInterceptor(
    private val userLocalDataSource: UserLocalDataSource,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val token = userLocalDataSource.getAuthorizationToken()

        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}
