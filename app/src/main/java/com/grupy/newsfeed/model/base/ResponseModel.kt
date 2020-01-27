package com.grupy.newsfeed.model.base


import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(
    @SerializedName("response")
    val response: ResponseSearch<T>
)