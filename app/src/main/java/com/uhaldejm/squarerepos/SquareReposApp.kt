package com.uhaldejm.squarerepos

import android.app.Application
import com.uhaldejm.squarerepos.data.*
import com.uhaldejm.squarerepos.data.database.SquareReposDatabase
import com.uhaldejm.squarerepos.data.utils.Merger
import com.uhaldejm.squarerepos.interactors.AddBookmarkInteractor
import com.uhaldejm.squarerepos.interactors.GetAllReposInteractor
import com.uhaldejm.squarerepos.interactors.GetRepoInteractor
import com.uhaldejm.squarerepos.interactors.RemoveBookmarkInteractor
import com.uhaldejm.squarerepos.presentation.repositoryDetail.RepoDetailViewModelFactory
import com.uhaldejm.squarerepos.presentation.repositoryList.RepoListAdapter
import com.uhaldejm.squarerepos.presentation.repositoryList.RepoListViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SquareReposApp() : Application(), KodeinAware {

    val API_URL = "https://api.github.com"

    override val kodein: Kodein = Kodein.lazy {

        import(androidXModule(this@SquareReposApp))

        //Utils
        bind<Merger>() with singleton { Merger() }

        //Database
        bind<SquareReposDatabase>() with singleton { SquareReposDatabase.create(instance()) }

        //Retrofit
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging) // <-- this is the important line!

        bind<Retrofit>() with singleton {
            Retrofit
                .Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        //Bookmarks
        bind<BookmarkDataSource>() with singleton { BookmarkDataSourceRoom(instance()) }
        bind<AddBookmarkInteractor>() with singleton { AddBookmarkInteractor(instance()) }
        bind<RemoveBookmarkInteractor>() with singleton { RemoveBookmarkInteractor(instance()) }

        //Repos
        bind<RepoDataSource>() with singleton { RepoDataSourceNetwork(instance()) }
        bind<RepoRepositoryImpl>() with singleton {
            RepoRepositoryImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind<GetAllReposInteractor>() with singleton { GetAllReposInteractor(instance()) }
        bind<GetRepoInteractor>() with singleton { GetRepoInteractor(instance()) }
        bind<RepoListAdapter>() with provider { RepoListAdapter() }

        //ViewModels
        bind<RepoDetailViewModelFactory>() with provider {
            RepoDetailViewModelFactory(instance(), instance(), instance())
        }
        bind<RepoListViewModelFactory>() with provider {
            RepoListViewModelFactory(instance())
        }
    }

}