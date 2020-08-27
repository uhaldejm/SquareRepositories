package com.uhaldejm.squarerepos.presentation.viewEntities

import com.uhaldejm.squarerepos.domain.Bookmark
import java.io.Serializable

data class RepoView (val id: Int, val name:String, val starsCount:Int, val bookmark:Bookmark?):Serializable{
    fun isBookmarked() = bookmark!=null
}