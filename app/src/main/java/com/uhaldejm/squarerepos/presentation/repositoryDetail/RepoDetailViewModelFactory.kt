package com.uhaldejm.squarerepos.presentation.repositoryDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uhaldejm.squarerepos.interactors.AddBookmarkInteractor
import com.uhaldejm.squarerepos.interactors.GetRepoInteractor
import com.uhaldejm.squarerepos.interactors.RemoveBookmarkInteractor

class RepoDetailViewModelFactory(
    private val addBookmarkInteractor: AddBookmarkInteractor,
    private val removeBookmarkInteractor: RemoveBookmarkInteractor,
    private val getRepoInteractor: GetRepoInteractor
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoDetailViewModel(
            addBookmarkInteractor,
            removeBookmarkInteractor,
            getRepoInteractor
        ) as T
    }


}