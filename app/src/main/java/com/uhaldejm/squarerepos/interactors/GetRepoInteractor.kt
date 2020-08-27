package com.uhaldejm.squarerepos.interactors

import com.uhaldejm.squarerepos.data.RepoRepository
import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.data.utils.Result.*
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRepoInteractor(private val repoRepository: RepoRepository) {

    operator fun invoke(name: String): Flow<Result<RepoView>> = repoRepository.getRepo(name).map {
        when (it) {
            is Error -> Error(it.exception)
            is Loading -> Loading(it.isLoading)
            is Success -> Success(it.data.asView())
        }
    }

}