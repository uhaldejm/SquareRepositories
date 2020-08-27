package com.uhaldejm.squarerepos.data

import com.uhaldejm.squarerepos.data.utils.Merger
import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.data.utils.Result.*
import com.uhaldejm.squarerepos.domain.Repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RepoRepositoryImpl(
    private val repoDataSource: RepoDataSource,
    private val bookmarkDataSource: BookmarkDataSource,
    private val merger: Merger
) : RepoRepository {

    override fun getRepos(): Flow<Result<List<Repo>>> {
        val repoFlow = repoDataSource.getAll()
        val bookmarkFlow = bookmarkDataSource.getAll()
        return repoFlow
            .combine(bookmarkFlow) { repos, bookmarks ->
            when(repos){
                is Error,
                is Loading -> repos
                is Success -> {
                    Success(
                    merger.merge(
                        repos.data,
                        (bookmarks as Success).data))
                }
            }
        }
    }

    override fun getRepo(name: String): Flow<Result<Repo>> {
        val repoFlow = repoDataSource.get(name)
        val bookmarkFlow = bookmarkDataSource.get(name)
        return repoFlow.combine(bookmarkFlow) { repo, bookmark ->
            when(repo){
                is Error,
                is Loading -> repo
                is Success -> {
                    Success(
                        merger.merge(
                            repo.data,
                            (bookmark as Success).data))
                }
            }
        }
    }

    override fun addBookmark(name: String) {
        CoroutineScope(IO).launch {
            bookmarkDataSource.addBookmark(name)
        }
    }

    override fun removeBookmark(name:String) {
        CoroutineScope(IO).launch {
            bookmarkDataSource.removeBookmark(name)
        }
    }

}