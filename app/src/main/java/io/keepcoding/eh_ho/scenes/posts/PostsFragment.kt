package io.keepcoding.eh_ho.scenes.posts

import android.content.Context
import android.nfc.tech.MifareUltralight
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onResume() {
        super.onResume()

        viewModel.fetchTopicDetail()
    }

    private fun initialize() {
        viewModel.delegate = this
        viewModel.initialize(topic)

        (activity as AppCompatActivity).supportActionBar?.title = topic.title

        listPosts.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listPosts.adapter = postsAdapter
    }

    private fun setListeners() {
        buttonRetry.setOnClickListener {
            retryLoadPosts()
        }

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            viewModel.fetchTopicDetail()
        }

        listPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                listPosts.layoutManager?.let {
                    val visibleItemCount: Int = it.childCount
                    val totalItemCount: Int = it.itemCount
                    val firstVisibleItemPosition: Int =
                        (it as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0 && totalItemCount >= MifareUltralight.PAGE_SIZE
                    ) {
                        viewModel.fetchMorePosts()
                    }
                }
            }
        })
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
        viewModel.fetchTopicDetail()
    }

    // PostsViewModelDelegate
    override fun updatePosts() {
        postsAdapter.setPosts(viewModel.posts)
        showError(false)
        swipeRefresh.isRefreshing = false
    }

    override fun updateLoadingState(show: Boolean) {
        enableLoading(show)
    }

    override fun onErrorGettingTopicDetail() {
        showError()
    }

    override fun onErrorGettingPosts() {
        showError()
    }

}