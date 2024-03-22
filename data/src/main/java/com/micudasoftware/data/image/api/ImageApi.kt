package com.micudasoftware.data.image.api

import com.micudasoftware.data.image.api.dto.UploadImageResponseDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Api for image operations.
 */
interface ImageApi {

    @Multipart
    @POST("/image")
    suspend fun uploadImage(@Part image: MultipartBody.Part): Response<UploadImageResponseDto>
}
