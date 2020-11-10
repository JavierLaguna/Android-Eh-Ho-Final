package io.keepcoding.eh_ho.scenes.posts

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.Topic
import io.keepcoding.eh_ho.utils.CustomViewModelFactory
import io.keepcoding.eh_ho.utils.inflate
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.fragment_posts.swipeRefresh
import kotlinx.android.synthetic.main.fragment_posts.viewError
import kotlinx.android.synthetic.main.fragment_posts.viewLoading
import kotlinx.android.synthetic.main.view_error.*
import java.lang.IllegalArgumentException

class PostsFragment(private val topic: Topic) : Fragment(), PostsViewModelDelegate {

    // PostsInteractionListener
    interface PostsInteractionListener {
        fun onCreatePost()
    }

    private val viewModel: PostsViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application, this)
        ViewModelProvider(this, factory).get(PostsViewModel::class.java)
    }
    private val postsAdapter: PostsAdapter by lazy {
        val adapter = PostsAdapter()
        adapter
    }
    private var postsInteractionListener: PostsInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is PostsInteractionListener) {
            postsInteractionListener = context
        } else {
            throw IllegalArgumentException("Context doesn´t implement ${PostsInteractionListener::class.java.canonicalName}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_posts)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setListeners()
    }

//    override fun onResume() {
//        super.onResume()
//
//        loadPosts()
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create_post, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_create_post -> postsInteractionListener?.onCreatePost()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initialize() {
        viewModel.delegate = this
        viewModel.initialize(topic)

        listPosts.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listPosts.adapter = postsAdapter
    }

    private fun setListeners() {
        buttonRetry.setOnClickListener {
            retryLoadPosts()
        }

        swipeRefresh.setOnRefreshListener {
            loadPosts()
        }
    }

    private fun loadPosts() { // TODO
//        context?.let {
//            enableLoading()
//
//            PostsRepo.getPosts(it.applicationContext, topicId.toString(), {
//                postsAdapter.setPosts(it)
//                enableLoading(false)
//                swipeRefresh.isRefreshing = false
//            }, {
//                swipeRefresh.isRefreshing = false
//                showError()
//            })
//        }
    }

    private fun enableLoading(enabled: Boolean = true) {
        if (enabled) {
            listPosts.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            listPosts.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    private fun showError(show: Boolean = true) {
        if (show) {
            viewError.visibility = View.VISIBLE
            listPosts.visibility = View.INVISIBLE
            viewLoading.visibility = View.INVISIBLE
        } else {
            viewError.visibility = View.INVISIBLE
        }
    }

    private fun retryLoadPosts() {
        showError(false)
        loadPosts()
    }

    // PostsViewModelDelegate
    override fun updatePosts() {
        postsAdapter.setPosts(viewModel.posts)
        showError(false)
        swipeRefresh.isRefreshing = false
    }

    override fun updateLoadingState(show: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onErrorGettingTopicDetail() {
        TODO("Not yet implemented")
    }

    override fun onErrorGettingPosts() {
        TODO("Not yet implemented")
    }

}