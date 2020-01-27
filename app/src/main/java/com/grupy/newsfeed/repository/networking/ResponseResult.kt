package com.grupy.newsfeed.repository.networking

import com.grupy.newsfeed.model.base.ResponseModel

/**
 * A generic class that holds a value with its loading status.
 *
 * ResponseResult is usually created by the Repository classes where they return
 * `LiveData<ResponseResult<T>>` to pass back the latest data to the UI with its fetch status.
 */

data class ResponseResult<out T>(val status: Status, val data: T?, val error: Throwable?) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): ResponseResult<T> {
            return ResponseResult(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable, data: T? = null): ResponseResult<T> {
            return ResponseResult(Status.ERROR, data, error)
        }

    }
}