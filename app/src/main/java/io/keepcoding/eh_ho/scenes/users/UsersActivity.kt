package io.keepcoding.eh_ho.scenes.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.scenes.topics.TopicsActivity
import io.keepcoding.eh_ho.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity(), UsersViewModelDelegate {

    private val viewModel: UsersViewModel by lazy {
        val factory = CustomViewModelFactory(application, this)
        ViewModelProvider(this, factory).get(UsersViewModel::class.java)
    }
    private val usersAdapter: UsersAdapter by lazy {
        val adapter = UsersAdapter()
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        initialize()
        setListeners()
    }

    private fun initialize() {
        viewModel.delegate = this
        viewModel.initialize()

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
    }

    private fun goToTopics() {
        val intent = Intent(this, TopicsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    // UsersViewModelDelegate
    override fun updateUsers() {
        usersAdapter.setUsers(viewModel.users)
    }

    override fun updateLoadingState(show: Boolean) {
    }

    override fun onErrorGettingUsers() {
    }
}