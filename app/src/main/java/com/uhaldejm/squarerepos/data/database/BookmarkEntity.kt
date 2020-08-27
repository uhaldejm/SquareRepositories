package com.uhaldejm.squarerepos.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo val name: String,
    @ColumnInfo val date: Date
)