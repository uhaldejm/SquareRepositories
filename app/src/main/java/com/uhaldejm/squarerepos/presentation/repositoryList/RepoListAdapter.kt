package com.uhaldejm.squarerepos.presentation.repositoryList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uhaldejm.squarerepos.R
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private val repos: MutableList<RepoView> = mutableListOf()
    private var context: Context? = null
    private var listener: RepoListAdapterListener? = null

    fun setContext(context: Context) {
        this.context = context
    }

    fun setListener(listener: RepoListAdapterListener) {
        this.listener = listener
    }

    fun setRepos(input: List<RepoView>) {
        repos.clear()
        repos.addAll(input)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo: RepoView = repos[position]

        holder.name?.text = repo.name
        holder.starCount?.text = repo.starsCount.toString()
        holder.repoContainer?.setOnClickListener { listener?.onRepoClicked(repos.getOrNull(position)) }

        if (repo.isBookmarked()) {
            holder.bookmarkOn?.visibility = View.VISIBLE
        } else {
            holder.bookmarkOn?.visibility = View.GONE
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.name
        val starCount: TextView = view.starCount
        val bookmarkOn: ImageView = view.bookmarkOn
        val repoContainer: LinearLayout = view.repoContainer
    }

    interface RepoListAdapterListener {
        fun onRepoClicked(repo: RepoView?)
    }

}

