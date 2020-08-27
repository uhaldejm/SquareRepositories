package com.uhaldejm.squarerepos.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = REPLACE)
    fun addBookmark(bookmark: BookmarkEntity)

    @Query("SELECT * FROM bookmarks WHERE name == :name")
    fun getBookmark(name: String): Flow<BookmarkEntity?>

    @Query("DELETE FROM bookmarks WHERE name = :name")
    fun removeBookmark(name: String)

    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Flow<List<BookmarkEntity>>

}