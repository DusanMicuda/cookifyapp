package com.micudasoftware.domain.common

/**
 * Sealed class representing the Result of an operation (e.g. api call).
 *
 * @param T The data returned by the operation.
 */
sealed class Result<T> {

    /**
     * Success result.
     *
     * @property data The returned data.
     */
    data class Success<T>(val data: T) : Result<T>()

    /**
     * Error Result.
     *
     * @property code The error code.
     * @property message The error message.
     * @property throwable The exception throwable.
     */
    data class Error<T>(
        val code: Int = -1,
        val message: String,
        val throwable: Throwable? = null
    ) : Result<T>()

    /**
     * Inline function to run a [block] only when [Result] is [Success].
     *
     * @param block Lambda function to be executed.
     * @return Same [Result] from which was this function called.
     */
    inline fun onSuccess(block: (T) -> Unit): Result<T> {
        if (this is Success) {
            block(this.data)
        }
        return this
    }

    /**
     * Inline function to run a [block] only when [Result] is [Error].
     *
     * @param block Lambda function to be executed.
     * @return Same [Result] from which was this function called.
     */
    inline fun onError(block: (Error<T>) -> Unit): Result<T> {
        if (this is Error) {
            block(this)
        }
        return this
    }

    /**
     * Inline function to run a [block] after the operation finished.
     *
     * @param block Lambda function to be executed.
     * @return Same [Result] from which was this function called.
     */
    inline fun onFinished(block: (Result<T>) -> Unit): Result<T> {
        block(this)
        return this
    }

    /**
     * Inline function to map [T] data into [S] if the [Result] was success.
     *
     * @param mapAction Lambda function to map [T] to [S].
     * @return The [Result] with [S] if is [Success], otherwise same [Error].
     */
    inline fun <S> map(mapAction: (T) -> S): Result<S> {
        return when (this) {
            is Success -> Success(mapAction(this.data))
            is Error -> Error(this.code, this.message, this.throwable)
        }
    }

    /**
     * Inline function to chain another operation if the [Result] was [Success].
     *
     * @param chainAction Lambda function to chain another operation.
     * @return The [Result] of the [chainAction] if first operation was [Success], otherwise [Error].
     */
    inline fun <S> chain(chainAction: (T) -> Result<S>): Result<S> {
        return when (this) {
            is Success -> chainAction(this.data)
            is Error -> Error(this.code, this.message, this.throwable)
        }
    }
}