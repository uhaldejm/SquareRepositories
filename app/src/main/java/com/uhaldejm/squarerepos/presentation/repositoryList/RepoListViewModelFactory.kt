package com.uhaldejm.squarerepos.presentation.repositoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uhaldejm.squarerepos.interactors.GetAllReposInteractor

class RepoListViewModelFactory(
    private val getAllReposInteractor: GetAllReposInteractor
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoListViewModel(
            getAllReposInteractor
        ) as T
    }

}