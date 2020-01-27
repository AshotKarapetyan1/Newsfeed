package com.grupy.newsfeed.repository.networking

import android.content.Intent
import com.grupy.newsfeed.App
import com.grupy.newsfeed.ui.mainActivity.MainActivity
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.net.SocketTimeoutException

class NetworkError {

    companion object {
        fun isServerError(error: Throwable): Boolean {
            return isAuthFailure(error) || error is SocketTimeoutException
        }

        fun isNetworkError(error: Throwable): Boolean {
            return isAuthFailure(error) || error is IOException
        }

        fun isAuthFailure(error: Throwable): Boolean {
            if (error is HttpException) {
                if (error.code() == HTTP_UNAUTHORIZED || error.code() == HTTP_FORBIDDEN) {
                    val intent = Intent(App.applicationContext(), MainActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                    App.applicationContext().startActivity(intent)
                    return true
                } else
                    return false
            } else
                return false
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}