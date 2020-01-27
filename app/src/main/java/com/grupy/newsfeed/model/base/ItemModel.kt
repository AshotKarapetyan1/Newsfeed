package com.grupy.newsfeed.model.base


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "news_table")
data class ItemModel(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("apiUrl")
    val apiUrl: String,
    @SerializedName("isHosted")
    val isHosted: Boolean,
    @SerializedName("pillarId")
    val pillarId: String,
    @SerializedName("pillarName")
    val pillarName: String,
    @SerializedName("sectionId")
    val sectionId: String,
    @SerializedName("sectionName")
    val sectionName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("webPublicationDate")
    val webPublicationDate: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String,
    var isFav: Boolean = false
): Serializable