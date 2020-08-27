package com.uhaldejm.squarerepos.data

import com.uhaldejm.squarerepos.data.api.GithubAPI
import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.data.utils.Result.*
import com.uhaldejm.squarerepos.domain.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit

class RepoDataSourceNetwork(private val retrofit: Retrofit):RepoDataSource{

    private val githubAPI by lazy { retrofit.create(GithubAPI::class.java) }

    override fun getAll(): Flow<Result<List<Repo>>> {
        return flow{
            emit(Loading(true))
            try {
                val repos = githubAPI.getAllRepos()
                emit(Loading(false))
                emit(Success(repos))
            } catch (e: Exception) {
                emit(Error(e))
            }
        }
    }

    override fun get(name: String): Flow<Result<Repo>> {
        return flow{
            emit(Loading(true))
            try {
                val repo = githubAPI.getRepo(name)
                emit(Loading(false))
                emit(Success(repo))
            } catch (e: Exception) {
                emit(Error(e))
            }
        }
    }

}