package io.keepcoding.eh_ho.scenes.topics

import android.content.Context
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.Topic
import io.keepcoding.eh_ho.utils.CustomViewModelFactory
import io.keepcoding.eh_ho.utils.inflate
import kotlinx.android.synthetic.main.fragment_topics.*
import kotlinx.android.synthetic.main.view_error.view.*


class TopicsFragment : Fragment(), TopicsViewModelDelegate {

    // TopicsInteractionListener
    interface TopicsInteractionListener {
        fun onCreateTopic()
        fun onShowPosts(topic: Topic)
        fun onLogout()
    }

    private val fetchNextPageOffset = 6
    private val viewModel: TopicsViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application, this)
        ViewModelProvider(this, factory).get(TopicsViewModel::class.java)
    }
    private val topicsAdapter: TopicsAdapter by lazy {
        val adapter = TopicsAdapter {
            topicsInteractionListener?.onShowPosts(it)
        }
        adapter
    }
    private var topicsInteractionListener: TopicsInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TopicsInteractionListener) {
            topicsInteractionListener = context
        } else {
            throw IllegalArgumentException("Context doesn´t implement ${TopicsInteractionListener::class.java.canonicalName}")
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
        return container?.inflate(R.layout.fragment_topics)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setListeners()
    }

    override fun onResume() {
        super.onResume()

        viewModel.refreshTopics()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_topics, menu)

        val search = menu.findItem(R.id.topicsSearchBar)
        val searchView = search.actionView as SearchView
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

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> topicsInteractionListener?.onLogout()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDetach() {
        topicsInteractionListener = null
        viewModel.delegate = null

        super.onDetach()
    }

    private fun initialize() {
        viewModel.delegate = this

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        listTopics.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listTopics.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        listTopics.adapter = topicsAdapter
    }

    private fun setListeners() {
        buttonCreate.setOnClickListener {
            topicsInteractionListener?.onCreateTopic()
        }

        viewError.buttonRetry.setOnClickListener {
            retryLoadTopics()
        }

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            viewModel.refreshTopics()
        }

        listTopics.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                listTopics.layoutManager?.let {
                    val visibleItemCount: Int = it.childCount
                    val totalItemCount: Int = it.itemCount
                    val firstVisibleItemPosition: Int =
                        (it as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition + fetchNextPageOffset >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount + fetchNextPageOffset >= PAGE_SIZE
                    ) {
                        viewModel.fetchMoreTopics()
                    }
                }
            }
        })
    }

    private fun retryLoadTopics() {
        showError(false)
        viewModel.refreshTopics()
    }

    private fun enableLoading(enabled: Boolean = true) {
        if (enabled) {
            listTopics.visibility = View.INVISIBLE
            buttonCreate.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            listTopics.visibility = View.VISIBLE
            buttonCreate.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    private fun showError(show: Boolean = true) {
        swipeRefresh.isRefreshing = false

        if (show) {
            viewError.visibility = View.VISIBLE
            buttonCreate.visibility = View.VISIBLE
            listTopics.visibility = View.INVISIBLE
            viewLoading.visibility = View.INVISIBLE
        } else {
            viewError.visibility = View.INVISIBLE
        }
    }

    // TopicsViewModelDelegate
    override fun updateTopics() {
        topicsAdapter.setTopics(viewModel.filteredTopics)
        showError(false)
        swipeRefresh.isRefreshing = false
    }

    override fun updateLoadingState(show: Boolean) {
        enableLoading(show)
    }

    override fun onErrorGettingTopics() {
        showError()
    }
}

class ScrollAwareFABBehavior(context: Context?, attrs: AttributeSet?) :
    FloatingActionButton.Behavior(context, attrs) {

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )

        if (dyConsumed > 0) {
            val params = child.layoutParams as CoordinatorLayout.LayoutParams
            val toTranslate = child.height.toFloat() + params.bottomMargin
            child.animate().translationY(toTranslate)
        } else if (dyConsumed < 0) {
            child.animate().translationY(0F)
        }
    }
}