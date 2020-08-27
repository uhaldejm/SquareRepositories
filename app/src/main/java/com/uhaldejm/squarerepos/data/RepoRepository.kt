package com.uhaldejm.squarerepos.data

import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.domain.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    fun getRepos(): Flow<Result<List<Repo>>>

    fun getRepo(name: String): Flow<Result<Repo>>

    fun addBookmark(name: String)

    fun removeBookmark(name: String)

}