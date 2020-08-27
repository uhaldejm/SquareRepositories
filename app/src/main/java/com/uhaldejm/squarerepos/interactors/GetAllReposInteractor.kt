package com.uhaldejm.squarerepos.interactors

import com.uhaldejm.squarerepos.data.RepoRepository
import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllReposInteractor(private val repoRepositoryImpl: RepoRepository) {

    operator fun invoke(): Flow<Result<List<RepoView>>> = repoRepositoryImpl.getRepos().map { list ->
        when(list){
            is Result.Error -> Result.Error(list.exception)
            is Result.Loading -> Result.Loading(list.isLoading)
            is Result.Success -> Result.Success(list.data.map{ it.asView() })
        }
    }

}