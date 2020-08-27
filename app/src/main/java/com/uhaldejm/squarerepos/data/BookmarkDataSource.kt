package com.uhaldejm.squarerepos.data

import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.domain.Bookmark
import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {

    fun addBookmark(name:String)

    fun removeBookmark(name:String)

    fun getAll(): Flow<Result<List<Bookmark>>>

    fun get(name:String): Flow<Result<Bookmark?>>

}