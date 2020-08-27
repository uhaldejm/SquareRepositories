package com.uhaldejm.squarerepos.data

import com.uhaldejm.squarerepos.data.utils.Result
import com.uhaldejm.squarerepos.domain.Repo
import kotlinx.coroutines.flow.Flow

interface RepoDataSource{

    fun getAll():Flow<Result<List<Repo>>>

    fun get(name:String):Flow<Result<Repo>>
}