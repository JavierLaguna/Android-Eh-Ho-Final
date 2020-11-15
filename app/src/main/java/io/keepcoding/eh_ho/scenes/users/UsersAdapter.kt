package io.keepcoding.eh_ho.scenes.users

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.utils.inflate
import kotlinx.android.synthetic.main.item_user.view.*


class UsersAdapter(private val userClickListener: ((User) -> Unit)?) :
    RecyclerView.Adapter<UsersAdapter.UserHolder>() {

    private val users = mutableListOf<User>()

    private val listener: ((View) -> Unit) = {
        if (it.tag is User) {
            val user: User = it.tag as User
            userClickListener?.invoke(user)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.UserHolder {
        val view = parent.inflate(R.layout.item_user)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UsersAdapter.UserHolder, position: Int) {
        val user = users[position]

        holder.user = user
        holder.itemView.setOnClickListener(listener)
    }

    fun setUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    // ViewHolder
    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var user: User? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let { user ->
                    val avatarUrl = user.userInfo?.getAvatarURL()
                    avatarUrl?.let {
                        Glide.with(itemView.context).load(avatarUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(itemView.imageUser)
                    }

                    itemView.userNameLabel.text = user.userInfo?.name ?: user.userInfo?.username
                }
            }
    }
}