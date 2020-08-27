package com.uhaldejm.squarerepos.interactors

import com.uhaldejm.squarerepos.data.RepoRepository

class AddBookmarkInteractor(private val repoRepository: RepoRepository) {

    operator fun invoke(name: String):Unit = repoRepository.addBookmark(name)

}