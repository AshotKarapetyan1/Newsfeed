package com.grupy.newsfeed.repository.services

import com.armboldmind.taponam.repository.networking.BaseDataSource
import com.grupy.newsfeed.repository.api.ISearchService

import com.grupy.newsfeed.repository.networking.NetworkModule

class SearchService : BaseDataSource() {

    private val mISearchService: ISearchService by lazy {
        NetworkModule.retrofit().create(ISearchService::class.java)
    }

    suspend fun search(apiKey: String) = getResult { mISearchService.search(apiKey) }
}