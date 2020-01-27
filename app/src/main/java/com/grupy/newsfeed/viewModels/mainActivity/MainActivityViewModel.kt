package com.grupy.newsfeed.viewModels.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupy.newsfeed.App
import com.grupy.newsfeed.model.base.ItemModel
import com.grupy.newsfeed.repository.services.NewsRoomDatabase
import com.grupy.newsfeed.repository.services.NewsRoomRepository
import com.grupy.newsfeed.repository.networking.ResponseResult
import com.grupy.newsfeed.repository.services.SearchService
import com.grupy.newsfeed.shared.utils.AppConstants
import com.grupy.newsfeed.viewModels.base.BaseViewModel
import kotlinx.coroutines.launch

class MainActivityViewModel : BaseViewModel() {

    private val service = SearchService()
    private val repository: NewsRoomRepository

    val newsLiveData: LiveData<List<ItemModel>>
    val favNewListLiveData: LiveData<List<ItemModel>>

    init {
        val itemDao = NewsRoomDatabase.getDatabase(App.applicationContext()).itemDao()
        repository =
            NewsRoomRepository(itemDao)
        newsLiveData = repository.allItems
        favNewListLiveData = repository.favItems
    }

    fun search() {
        viewModelScope.launch {
            isLoading(true)
            val response = service.search(AppConstants.API_KEY)
            when (response.status) {
                ResponseResult.Status.SUCCESS ->insert(response.data?.response?.results!!)
                ResponseResult.Status.ERROR -> errorView(response.error)
            }
            isLoading(false)
        }
    }

    fun insert(items: List<ItemModel>) = viewModelScope.launch {
        repository.insert(items)
    }

    fun update(item: ItemModel) = viewModelScope.launch {
        repository.update(item)
    }
}