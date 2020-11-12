package io.keepcoding.eh_ho.scenes.users

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.utils.inflate
import kotlinx.android.synthetic.main.item_user.view.*


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserHolder>() {

    private val users = mutableListOf<User>()

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.UserHolder {
        val view = parent.inflate(R.layout.item_user)
        return UserHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: UsersAdapter.UserHolder, position: Int) {
        val user = users[position]

        holder.user = user
    }

    fun setUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    // ViewHolder
    inner class UserHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        private val glide = Glide.with(context)
        var user: User? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let { user ->
                    itemView.userNameLabel.text = user.userInfo?.name ?: user.userInfo?.username

                    val avatarUrl = user.userInfo?.getAvatarURL()
                    avatarUrl?.let {
                        glide.load(avatarUrl).into(itemView.imageUser)
                    }
                }
            }
    }
}