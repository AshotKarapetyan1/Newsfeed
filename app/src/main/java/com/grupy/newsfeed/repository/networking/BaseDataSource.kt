package com.armboldmind.taponam.repository.networking


import com.grupy.newsfeed.App
import com.grupy.newsfeed.R
import com.grupy.newsfeed.model.base.ResponseModel
import com.grupy.newsfeed.repository.networking.ResponseResult
import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    @Suppress("UNREACHABLE_CODE")
    protected suspend fun <T> getResult(call: suspend () -> Response<ResponseModel<T>>): ResponseResult<ResponseModel<T>> {
        try {
            val response = call()
            if (response.isSuccessful) {
                if (response.body()?.response?.status == "ok" && response.body()?.response?.results != null)
                    return ResponseResult.success(response.body()!!)
            }

            return error(throw Exception(App.applicationContext().getString(R.string.default_error_message)))
        } catch (e: Exception) {
            return error(e)
        }
    }

    private fun <T> error(message: Throwable): ResponseResult<T> {
        return ResponseResult.error(message)
    }

}

