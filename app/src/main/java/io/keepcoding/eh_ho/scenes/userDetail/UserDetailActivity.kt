package io.keepcoding.eh_ho.scenes.userDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_user_detail.*


class UserDetailActivity : AppCompatActivity(), UserDetailViewModelDelegate {

    companion object {
        val EXTRA_USER = "USER"
    }

    private val viewModel: UserDetailViewModel by lazy {
        val factory = CustomViewModelFactory(application, this)
        ViewModelProvider(this, factory).get(UserDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        initialize()
    }

    private fun initialize() {
        val user = intent.getSerializableExtra(EXTRA_USER) as User

        viewModel.delegate = this
        viewModel.initialize(user)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun setUserInfoUI() {
        Glide.with(this).load(viewModel.avatarUrl).into(userAvatarImage)

        nickLabel.text = viewModel.nickname
        nameLabel.text = viewModel.name
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