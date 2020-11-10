package io.keepcoding.eh_ho.scenes.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.Topic
import io.keepcoding.eh_ho.scenes.createPost.CreatePostFragment
import io.keepcoding.eh_ho.utils.isFirstTimeCreated

const val EXTRA_TOPIC = "TOPIC"
const val TRANSACTION_CREATE_POST = "create_post"

class PostsActivity : AppCompatActivity(), PostsFragment.PostsInteractionListener,
    CreatePostFragment.CreatePostInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        if (isFirstTimeCreated(savedInstanceState)) {
            val topic = intent.getSerializableExtra(EXTRA_TOPIC) as Topic

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, PostsFragment(topic))
                .commit()
        }
    }

    // PostsInteractionListener
    override fun onCreatePost() {
//        val topicId = intent.getStringExtra(EXTRA_TOPIC_ID) ?: "" // TODO
//        val topicId = intent.getIntExtra(EXTRA_TOPIC_ID, -1)
//        val topicTitle = intent.getStringExtra(EXTRA_TOPIC_TITLE) ?: ""
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, CreatePostFragment(topicId.toString(), topicTitle))
//            .addToBackStack(TRANSACTION_CREATE_POST)
//            .commit()
    }

    // CreatePostInteractionListener
    override fun onPostCreated() {
        supportFragmentManager.popBackStack()
    }
}