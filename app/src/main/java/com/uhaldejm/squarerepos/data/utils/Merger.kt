package com.uhaldejm.squarerepos.data.utils

import com.uhaldejm.squarerepos.domain.Bookmark
import com.uhaldejm.squarerepos.domain.Repo

class Merger {

    fun merge(repos: List<Repo>, bookmarks: List<Bookmark>): List<Repo> {
        val map: Map<String, Bookmark> = bookmarks.map { it.name to it }.toMap()
        for (r in repos) {
            r.bookmark = map[r.name]
        }
        return repos
    }

    fun merge(repo: Repo, bookmark: Bookmark?): Repo {
        repo.bookmark = bookmark
        return repo
    }

}