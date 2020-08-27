package com.uhaldejm.squarerepos.interactors

import com.uhaldejm.squarerepos.data.RepoRepository

class RemoveBookmarkInteractor(private val repoRepository: RepoRepository) {

    operator fun invoke(name: String):Unit = repoRepository.removeBookmark(name)

}