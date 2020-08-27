package com.uhaldejm.squarerepos.presentation.repositoryList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.uhaldejm.squarerepos.R
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class RepoListFragment : Fragment(), KodeinAware, RepoListAdapter.RepoListAdapterListener {

    companion object {
        fun newInstance() = RepoListFragment()
    }

    override val kodein: Kodein by closestKodein()
    private val repoListViewModelFactory: RepoListViewModelFactory by instance()
    private val adapter: RepoListAdapter by instance()

    private var listener: RepoListFragmentListener? = null
    private lateinit var viewModel: RepoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RepoListFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    private fun initializeUI() {

        //Setup Recyclerview
        adapter.setContext(requireContext())
        adapter.setListener(this)
        listList.layoutManager = LinearLayoutManager(context)
        val itemDecor = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        listList.addItemDecoration(itemDecor)
        listList.adapter = adapter

        //Initialize ViewModel
        viewModel =
            ViewModelProviders.of(this, repoListViewModelFactory).get(
                RepoListViewModel::class.java
            )
        //Get repos
        viewModel.repos.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                adapter.setRepos(it)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity,it, Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it){
                listLoading.visibility = View.VISIBLE
            }else{
                listLoading.visibility = View.GONE
            }
        })

    }

    override fun onRepoClicked(repo: RepoView?) {
        if (repo != null) {
            listener?.onDetailClicked(repo)
        }
    }

    interface RepoListFragmentListener {
        fun onDetailClicked(repo: RepoView)
    }
}

