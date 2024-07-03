package com.micudasoftware.cookifyapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application(), ImageLoaderFactory {

    @Inject
    lateinit var client: OkHttpClient

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .okHttpClient(client)
            .build()
    }
}
