package com.grupy.newsfeed.repository.api

import com.grupy.newsfeed.model.base.ResponseModel
import com.grupy.newsfeed.model.base.ItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchService{

    @GET("search")
    suspend fun search(@Query("api-key") apiKey: String): Response<ResponseModel<ItemModel>>
}
