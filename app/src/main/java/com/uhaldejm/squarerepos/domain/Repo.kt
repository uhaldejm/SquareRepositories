package com.uhaldejm.squarerepos.domain

import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView
import java.io.Serializable

data class Repo(val id: Int, val name: String, val stargazers_count: Int, var bookmark: Bookmark?) :
    Serializable {

    fun asView(): RepoView = RepoView(id, name, stargazers_count, bookmark)

}