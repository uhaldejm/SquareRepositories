package com.uhaldejm.squarerepos.presentation.repositoryDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.uhaldejm.squarerepos.R
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class RepoDetailFragment : Fragment(), KodeinAware {

    companion object {
        fun newInstance(repo: RepoView) = RepoDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("repo", repo)
            }
        }
    }

    override val kodein: Kodein by closestKodein()
    private val repoDetailViewModelFactory: RepoDetailViewModelFactory by instance()

    private lateinit var viewModel: RepoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        //Initialize ViewModel
        viewModel =
            ViewModelProviders.of(this, repoDetailViewModelFactory).get(
                RepoDetailViewModel::class.java
            )

        //Show detail
        val r: RepoView = arguments?.getSerializable("repo") as RepoView
        showRepo(r)
        viewModel.start(r.name)

        viewModel.repo.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                showRepo(it)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity,it, Toast.LENGTH_LONG).show()
        })

        bookmarkOn.setOnClickListener {
            Log.e("MSJ", "Remove bookmark!")
            viewModel.removeBookmark(r.name) }
        bookmarkOff.setOnClickListener {
            Log.e("MSJ", "Add bookmark!")
            viewModel.addBookmark(r.name) }
    }

    private fun showRepo(repo: RepoView){
        name.text = repo.name
        starCount.text = repo.starsCount.toString()
        showAsMarked(repo.isBookmarked())
    }

    private fun showAsMarked(bookmarked: Boolean) {
        Log.e("MSJ", "Marked? $bookmarked")
        if (bookmarked) {
            bookmarkOn?.visibility = View.VISIBLE
            bookmarkOff?.visibility = View.GONE
        } else {
            bookmarkOn?.visibility = View.GONE
            bookmarkOff?.visibility = View.VISIBLE
        }
    }

}
