package com.uhaldejm.squarerepos.presentation.repositoryDetail

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.uhaldejm.squarerepos.R
import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.data.utils.Result.*
import com.uhaldejm.squarerepos.interactors.AddBookmarkInteractor
import com.uhaldejm.squarerepos.interactors.GetRepoInteractor
import com.uhaldejm.squarerepos.interactors.RemoveBookmarkInteractor
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView

class RepoDetailViewModel(
    private val addBookmarkInteractor: AddBookmarkInteractor,
    private val removeBookmarkInteractor: RemoveBookmarkInteractor,
    private val getRepoInteractor: GetRepoInteractor
) : ViewModel() {

    private val _repoName:MutableLiveData<String> = MutableLiveData()

    private var _repo: LiveData<RepoView?> = _repoName.switchMap { _repoName ->
        getRepoInteractor.invoke(_repoName).asLiveData().map { computeResult(it) }
    }
    var repo: LiveData<RepoView?> = _repo

    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var loading: LiveData<Boolean> = _loading

    private var _error: MutableLiveData<Int> = MutableLiveData()
    var error: LiveData<Int> = _error

    fun addBookmark(name: String) = addBookmarkInteractor.invoke(name)

    fun removeBookmark(name: String) = removeBookmarkInteractor.invoke(name)

    fun start(name: String?) {
        if (_loading.value == true || name == _repoName.value) {
            return
        }
        _repoName.value = name
    }

    private fun computeResult(taskResult: Result<RepoView>): RepoView? {
        return when(taskResult){
            is Error-> showError(R.string.generic_error)
            is Loading-> showLoading(taskResult.isLoading)
            is Success-> taskResult.data
        }
    }

    private fun showError(@StringRes message:Int):RepoView? {
        _error.value = message
        return null
    }

    private fun showLoading(show:Boolean): RepoView? {
        _loading.value=show
        return null
    }

}
