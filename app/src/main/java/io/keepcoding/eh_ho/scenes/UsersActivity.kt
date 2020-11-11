package io.keepcoding.eh_ho.scenes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.scenes.topics.TopicsActivity
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        setListeners()
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
        overridePendingTransition(0,0)
        finish()
    }
}