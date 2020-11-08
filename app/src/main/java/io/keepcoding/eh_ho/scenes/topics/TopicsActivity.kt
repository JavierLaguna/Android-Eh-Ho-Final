package io.keepcoding.eh_ho.scenes.topics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.eh_ho.*
import io.keepcoding.eh_ho.models.Topic
import io.keepcoding.eh_ho.data.UserRepo
import io.keepcoding.eh_ho.scenes.createTopic.CreateTopicFragment
import io.keepcoding.eh_ho.scenes.login.LoginActivity
import io.keepcoding.eh_ho.scenes.posts.EXTRA_TOPIC_ID
import io.keepcoding.eh_ho.scenes.posts.EXTRA_TOPIC_TITLE
import io.keepcoding.eh_ho.scenes.posts.PostsActivity
import io.keepcoding.eh_ho.utils.isFirstTimeCreated

const val TRANSACTION_CREATE_TOPIC = "create_topic"

class TopicsActivity : AppCompatActivity(), TopicsFragment.TopicsInteractionListener,
    CreateTopicFragment.CreateTopicInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)

        if (isFirstTimeCreated(savedInstanceState)) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, TopicsFragment())
                .commit()
        }
    }

    fun goToPosts(topic: Topic) {
        val intent = Intent(this, PostsActivity::class.java)
        intent.putExtra(EXTRA_TOPIC_ID, topic.id)
        intent.putExtra(EXTRA_TOPIC_TITLE, topic.title)
        startActivity(intent)
    }

    // TopicsInteractionListener
    override fun onCreateTopic() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CreateTopicFragment())
            .addToBackStack(TRANSACTION_CREATE_TOPIC)
            .commit()
    }

    override fun onShowPosts(topic: Topic) {
        goToPosts(topic)
    }

    override fun onLogout() {
        UserRepo.logout(applicationContext)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Esta actividad se destruye y no se queda en el backstack de actividades
    }

    // CreateTopicInteractionListener
    override fun onTopicCreated() {
        supportFragmentManager.popBackStack()
    }
}