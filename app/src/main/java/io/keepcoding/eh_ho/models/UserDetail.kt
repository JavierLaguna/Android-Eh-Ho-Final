package io.keepcoding.eh_ho.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity
data class UserDetail(

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("recent_time_read")
    val recentTimeRead: Int? = null,

    @field:SerializedName("moderator")
    val moderator: Boolean? = null,

    @field:SerializedName("trust_level")
    val trustLevel: Int? = null,

    @field:SerializedName("can_send_private_messages")
    val canSendPrivateMessages: Boolean? = null,

    @field:SerializedName("second_factor_enabled")
    val secondFactorEnabled: Boolean? = null,

    @field:SerializedName("uploaded_avatar_id")
    val uploadedAvatarId: Int? = null,

    @field:SerializedName("ignored")
    val ignored: Boolean? = null,

    @field:SerializedName("can_send_private_message_to_user")
    val canSendPrivateMessageToUser: Boolean? = null,

    @field:SerializedName("can_edit_email")
    val canEditEmail: Boolean? = null,

    @field:SerializedName("can_mute_user")
    val canMuteUser: Boolean? = null,

    @field:SerializedName("profile_view_count")
    val profileViewCount: Int? = null,

    @field:SerializedName("custom_avatar_upload_id")
    val customAvatarUploadId: Int? = null,

    @field:SerializedName("can_edit_name")
    val canEditName: Boolean? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("badge_count")
    val badgeCount: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: Date? = null,

    @field:SerializedName("admin")
    val admin: Boolean? = null,

    @field:SerializedName("locale")
    val locale: String? = null,

    @field:SerializedName("system_avatar_template")
    val systemAvatarTemplate: String? = null,

    @field:SerializedName("mailing_list_posts_per_day")
    val mailingListPostsPerDay: Int? = null,

    @field:SerializedName("can_change_bio")
    val canChangeBio: Boolean? = null,

    @field:SerializedName("has_title_badges")
    val hasTitleBadges: Boolean? = null,

    @field:SerializedName("muted")
    val muted: Boolean? = null,

    @field:SerializedName("avatar_template")
    val avatarTemplate: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("last_posted_at")
    val lastPostedAt: Date? = null,

    @field:SerializedName("second_factor_backup_enabled")
    val secondFactorBackupEnabled: Boolean? = null,

    @field:SerializedName("time_read")
    val timeRead: Int? = null,

    @field:SerializedName("can_edit")
    val canEdit: Boolean? = null,

    @field:SerializedName("custom_avatar_template")
    val customAvatarTemplate: String? = null,

    @field:SerializedName("pending_count")
    val pendingCount: Int? = null,

    @field:SerializedName("can_edit_username")
    val canEditUsername: Boolean? = null,

    @field:SerializedName("can_ignore_user")
    val canIgnoreUser: Boolean? = null,

    @field:SerializedName("last_seen_at")
    val lastSeenAt: Date? = null,

    @field:SerializedName("username")
    val username: String? = null

) : Serializable