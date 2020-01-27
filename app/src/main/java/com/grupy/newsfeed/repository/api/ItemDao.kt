package com.grupy.newsfeed.repository.api

import androidx.lifecycle.LiveData
import androidx.room.*
import com.grupy.newsfeed.model.base.ItemModel

@Dao
interface ItemDao {

    @Query("SELECT * from news_table")
    fun getItemList(): LiveData<List<ItemModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemsModel: List<ItemModel>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(itemsModel: ItemModel)

    @Query("SELECT * FROM news_table WHERE isFav = :isFav")
    fun getFavItem(isFav: Boolean = true): LiveData<List<ItemModel>>
}