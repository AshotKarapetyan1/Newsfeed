package com.grupy.newsfeed.repository.services

import androidx.lifecycle.LiveData
import com.grupy.newsfeed.model.base.ItemModel
import com.grupy.newsfeed.repository.api.ItemDao

class NewsRoomRepository(private val itemDao: ItemDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allItems: LiveData<List<ItemModel>> = itemDao.getItemList()
    val favItems: LiveData<List<ItemModel>> = itemDao.getFavItem()

    suspend fun insert(items: List<ItemModel>) {
        itemDao.insert(items)
    }

    suspend fun update(item: ItemModel){
        itemDao.update(item)
    }
}