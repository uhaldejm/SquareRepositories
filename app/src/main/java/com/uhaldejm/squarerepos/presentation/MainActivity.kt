package com.uhaldejm.squarerepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.uhaldejm.squarerepos.R
import com.uhaldejm.squarerepos.presentation.repositoryDetail.RepoDetailFragment
import com.uhaldejm.squarerepos.presentation.repositoryList.RepoListFragment
import com.uhaldejm.squarerepos.presentation.viewEntities.RepoView

class MainActivity : AppCompatActivity(), RepoListFragment.RepoListFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            showFragment(RepoListFragment.newInstance())
        }
    }

    override fun onDetailClicked(repo: RepoView) {
        showFragment(RepoDetailFragment.newInstance(repo))
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

}
