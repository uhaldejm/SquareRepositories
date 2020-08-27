package com.uhaldejm.squarerepos.data.api

import com.uhaldejm.squarerepos.domain.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI{

    @GET("/orgs/square/repos")
    suspend fun getAllRepos(): List<Repo>

    @GET("/repos/square/{name}")
    suspend fun getRepo(@Path("name") groupId:String): Repo

}