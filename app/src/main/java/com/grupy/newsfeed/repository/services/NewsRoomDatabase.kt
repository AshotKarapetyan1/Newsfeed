package com.grupy.newsfeed.repository.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupy.newsfeed.repository.api.ItemDao
import com.grupy.newsfeed.model.base.ItemModel

@Database(entities = arrayOf(ItemModel::class), version = 1, exportSchema = false)
abstract class NewsRoomDatabase : RoomDatabase(){
    abstract fun itemDao(): ItemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NewsRoomDatabase? = null

        fun getDatabase(context: Context): NewsRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsRoomDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}