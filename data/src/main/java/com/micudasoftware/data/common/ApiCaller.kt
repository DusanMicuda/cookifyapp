package com.micudasoftware.data.common

import com.micudasoftware.domain.common.Result
import retrofit2.Response

/**
 * Object that contains functions to call APIs with [Result] response.
 */
object ApiCaller {

    /**
     * Generic Function to call API with that returns [T], and map it to [Result].
     *
     * @param T The DTO which API returns.
     * @param apiCall The api call function.
     * @param errorMessage The error message to be returned if [apiCall] fails.
     * @return The [Result.Success] with [T] if [apiCall] succeeds, otherwise [Result.Error].
     */
    suspend fun <T: Any> callResult(
        apiCall: suspend () -> Response<T>,
        errorMessage: (Exception) -> String,
    ): Result<T> {
         return try {
             val response = apiCall()
             when {
                 response.isSuccessful -> {
                     response.body()?.let {
                         Result.Success(it)
                     } ?: Result.Error(message = "No Content")
                 }
                 else -> Result.Error(response.code(), response.message() ?: "")
             }
        } catch (e: Exception) {
            Result.Error(message = errorMessage(e), throwable = e)
        }
    }

    /**
     * Function to call API with that doesn't return anything, and map it to [Result].
     *
     * @param apiCall The api call function.
     * @param errorMessage The error message to be returned if [apiCall] fails.
     * @return The empty [Result.Success] if [apiCall] succeeds, otherwise [Result.Error].
     */
    @JvmName("callResultEmpty")
    suspend fun callResult(
        apiCall: suspend () -> Response<Unit>,
        errorMessage: (Exception) -> String,
    ): Result<Unit> {
        return try {
            val response = apiCall()
            when {
                response.isSuccessful -> Result.Success(Unit)
                else -> Result.Error(response.code(), response.message() ?: "")
            }
        } catch (e: Exception) {
            Result.Error(message = errorMessage(e), throwable = e)
        }
    }
}
