package io.keepcoding.eh_ho.scenes.userDetail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnticipateOvershootInterpolator
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_user_detail.*


class UserDetailActivity : AppCompatActivity(), UserDetailViewModelDelegate {

    companion object {
        const val EXTRA_USER = "USER"
    }

    private val viewModel: UserDetailViewModel by lazy {
        val factory = CustomViewModelFactory(application, this)
        ViewModelProvider(this, factory).get(UserDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupAnimation()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        initialize()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupAnimation() {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val enterTransition = Slide()
        enterTransition.slideEdge = Gravity.RIGHT
        enterTransition.duration = 1000
        enterTransition.interpolator = AnticipateOvershootInterpolator()
        window.enterTransition = enterTransition

        window.allowEnterTransitionOverlap = false
    }

    private fun initialize() {
        val user = intent.getSerializableExtra(EXTRA_USER) as User

        viewModel.delegate = this
        viewModel.initialize(user)

        supportActionBar?.title = ""

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun setUserInfoUI() {
        Glide.with(this).load(viewModel.avatarUrl).into(userAvatarImage)

        nickLabel.text = viewModel.nickname
        nameLabel.text = viewModel.name
        lastConnectionLabel.text = viewModel.lastConnection
        myLikesLabel.text = viewModel.likesReceived

        if (viewModel.isMod) {
            modBadgeContainer.visibility = View.VISIBLE
        } else {
            modBadgeContainer.visibility = View.GONE
        }
    }

    private fun showError() {
        Snackbar.make(container, R.string.error_default, Snackbar.LENGTH_SHORT).show()
    }

    // UserDetailViewModelDelegate
    override fun updateUserInfo() {
        setUserInfoUI()
    }

    override fun onErrorGettingUserDetail() {
        showError()
    }
}