package com.uhaldejm.squarerepos.data

import com.uhaldejm.squarerepos.data.database.BookmarkEntity
import com.uhaldejm.squarerepos.data.database.SquareReposDatabase
import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.data.utils.Result.Success
import com.uhaldejm.squarerepos.domain.Bookmark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*


class BookmarkDataSourceRoom(private val squareReposDatabase: SquareReposDatabase) : BookmarkDataSource {


    override fun addBookmark(name:String) {
        squareReposDatabase.bookmarkDao().addBookmark(BookmarkEntity(name = name, date = Date()))
    }

    override fun removeBookmark(name:String) {
        squareReposDatabase.bookmarkDao().removeBookmark(name)
    }

    override fun getAll(): Flow<Result<List<Bookmark>>> {

        val originalFlow = squareReposDatabase.bookmarkDao().getBookmarks().map { list -> list.map { Bookmark(it.name,it.date) } }
        return originalFlow.map { Success(it) }

    }

    override fun get(name: String): Flow<Result<Bookmark?>> {
        val originalFlow = squareReposDatabase.bookmarkDao().getBookmark(name).map { if (it!=null) Bookmark(it.name, it.date) else null }
        return originalFlow.map { Success(it) }
    }

}