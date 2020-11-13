package io.keepcoding.eh_ho.scenes.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.scenes.topics.TopicsActivity
import io.keepcoding.eh_ho.scenes.userDetail.UserDetailActivity
import io.keepcoding.eh_ho.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.activity_users.swipeRefresh
import kotlinx.android.synthetic.main.activity_users.viewError
import kotlinx.android.synthetic.main.activity_users.viewLoading
import kotlinx.android.synthetic.main.view_error.*

class UsersActivity : AppCompatActivity(), UsersViewModelDelegate {

    private val viewModel: UsersViewModel by lazy {
        val factory = CustomViewModelFactory(application, this)
        ViewModelProvider(this, factory).get(UsersViewModel::class.java)
    }
    private val usersAdapter: UsersAdapter by lazy {
        val adapter = UsersAdapter {
            goToUserDetail(it)
        }
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        initialize()
        setListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_users, menu)

        val search = menu?.findItem(R.id.topicsSearchBar)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchText = newText
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun initialize() {
        viewModel.delegate = this
        viewModel.initialize()

        supportActionBar?.title = getString(R.string.users)

        listUsers.layoutManager = GridLayoutManager(this, 2)
        listUsers.adapter = usersAdapter
    }

    private fun setListeners() {
        bottomNavigation.selectedItemId = R.id.tabUsers

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tabTopics -> goToTopics()
            }

            true
        }

        buttonRetry.setOnClickListener {
            retryLoadUsers()
        }

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            viewModel.refreshUsers()
        }
    }

    private fun goToTopics() {
        val intent = Intent(this, TopicsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    private fun goToUserDetail(user: User) {
        Intent(this, UserDetailActivity::class.java).apply {
            putExtra(UserDetailActivity.EXTRA_USER, user)
            startActivity(this)
        }
    }

    private fun enableLoading(enabled: Boolean = true) {
        if (enabled) {
            listUsers.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            listUsers.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    private fun showError(show: Boolean = true) {
        if (show) {
            viewError.visibility = View.VISIBLE
            listUsers.visibility = View.INVISIBLE
            viewLoading.visibility = View.INVISIBLE
        } else {
            viewError.visibility = View.INVISIBLE
        }
    }

    private fun retryLoadUsers() {
        showError(false)
        viewModel.refreshUsers()
    }

    // UsersViewModelDelegate
    override fun updateUsers() {
        usersAdapter.setUsers(viewModel.filteredUsers)
        showError(false)
        swipeRefresh.isRefreshing = false
    }

    override fun updateLoadingState(show: Boolean) {
        enableLoading(show)
    }

    override fun onErrorGettingUsers() {
        showError()
    }
}