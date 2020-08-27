package com.uhaldejm.squarerepos.presentation.repositoryList

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.uhaldejm.squarerepos.R
import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.interactors.GetAllReposInteractor
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView

class RepoListViewModel(
    private val getAllReposInteractorInteractor: GetAllReposInteractor
) :
    ViewModel() {

    private val _repos: LiveData<List<RepoView>?> by lazy { getAllReposInteractorInteractor.invoke().asLiveData().map { computeResult(it) } }
    var repos: LiveData<List<RepoView>?> = _repos

    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var loading: LiveData<Boolean> = _loading

    private var _error: MutableLiveData<Int> = MutableLiveData()
    var error: LiveData<Int> = _error

    private fun computeResult(taskResult: Result<List<RepoView>>): List<RepoView>? {
        return when(taskResult){
            is Result.Error -> showError(R.string.generic_error)
            is Result.Loading -> showLoading(taskResult.isLoading)
            is Result.Success -> taskResult.data
        }
    }

    private fun showError(@StringRes message:Int): List<RepoView>? {
        _error.value = message
        return null
    }

    private fun showLoading(show:Boolean): List<RepoView>? {
        _loading.value=show
        return null
    }
}
