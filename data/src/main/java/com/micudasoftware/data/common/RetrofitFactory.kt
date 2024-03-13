package com.micudasoftware.data.common

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The factory for Retrofit client.
 */
object RetrofitFactory {

    /**
     * Function to create the instance of Retrofit client.
     *
     * @param authInterceptor the Authorization interceptor.
     * @return The [Retrofit] client.
     */
    fun create(authInterceptor: AuthInterceptor): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.redactHeader("Authorization")

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://192.168.9.44:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}