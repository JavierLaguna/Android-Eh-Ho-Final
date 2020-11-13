package io.keepcoding.eh_ho.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class UserDetail(

    @field:SerializedName("recent_time_read")
    val recentTimeRead: Int? = null,

    @field:SerializedName("moderator")
    val moderator: Boolean? = null,

    @field:SerializedName("trust_level")
    val trustLevel: Int? = null,

    @field:SerializedName("tracked_category_ids")
    val trackedCategoryIds: List<Any?>? = null,

    @field:SerializedName("muted_tags")
    val mutedTags: List<Any?>? = null,

    @field:SerializedName("can_send_private_messages")
    val canSendPrivateMessages: Boolean? = null,

    @field:SerializedName("primary_group_flair_bg_color")
    val primaryGroupFlairBgColor: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("second_factor_enabled")
    val secondFactorEnabled: Boolean? = null,

    @field:SerializedName("uploaded_avatar_id")
    val uploadedAvatarId: Int? = null,

    @field:SerializedName("ignored")
    val ignored: Boolean? = null,

    @field:SerializedName("watching_first_post_tags")
    val watchingFirstPostTags: List<Any?>? = null,

    @field:SerializedName("can_send_private_message_to_user")
    val canSendPrivateMessageToUser: Boolean? = null,

    @field:SerializedName("tracked_tags")
    val trackedTags: List<Any?>? = null,

    @field:SerializedName("primary_group_flair_url")
    val primaryGroupFlairUrl: Any? = null,

    @field:SerializedName("can_edit_email")
    val canEditEmail: Boolean? = null,

    @field:SerializedName("can_mute_user")
    val canMuteUser: Boolean? = null,

    @field:SerializedName("profile_view_count")
    val profileViewCount: Int? = null,

    @field:SerializedName("featured_user_badge_ids")
    val featuredUserBadgeIds: List<Int?>? = null,

    @field:SerializedName("custom_avatar_upload_id")
    val customAvatarUploadId: Int? = null,

    @field:SerializedName("can_edit_name")
    val canEditName: Boolean? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("system_avatar_upload_id")
    val systemAvatarUploadId: Any? = null,

    @field:SerializedName("badge_count")
    val badgeCount: Int? = null,

    @field:SerializedName("user_api_keys")
    val userApiKeys: Any? = null,

    @field:SerializedName("created_at")
    val createdAt: Date? = null,

    @field:SerializedName("admin")
    val admin: Boolean? = null,

    @field:SerializedName("title")
    val title: Any? = null,

    @field:SerializedName("locale")
    val locale: String? = null,

    @field:SerializedName("muted_category_ids")
    val mutedCategoryIds: List<Any?>? = null,

    @field:SerializedName("watched_tags")
    val watchedTags: List<Any?>? = null,

    @field:SerializedName("muted_usernames")
    val mutedUsernames: List<Any?>? = null,

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

    @field:SerializedName("primary_group_flair_color")
    val primaryGroupFlairColor: Any? = null,

    @field:SerializedName("time_read")
    val timeRead: Int? = null,

    @field:SerializedName("can_edit")
    val canEdit: Boolean? = null,

    @field:SerializedName("custom_avatar_template")
    val customAvatarTemplate: String? = null,

    @field:SerializedName("ignored_usernames")
    val ignoredUsernames: List<Any?>? = null,

    @field:SerializedName("invited_by")
    val invitedBy: Any? = null,

    @field:SerializedName("watched_first_post_category_ids")
    val watchedFirstPostCategoryIds: List<Any?>? = null,

    @field:SerializedName("pending_count")
    val pendingCount: Int? = null,

    @field:SerializedName("primary_group_name")
    val primaryGroupName: Any? = null,

    @field:SerializedName("associated_accounts")
    val associatedAccounts: List<Any?>? = null,

    @field:SerializedName("can_edit_username")
    val canEditUsername: Boolean? = null,

    @field:SerializedName("can_ignore_user")
    val canIgnoreUser: Boolean? = null,

    @field:SerializedName("user_auth_tokens")
    val userAuthTokens: List<Any?>? = null,

    @field:SerializedName("last_seen_at")
    val lastSeenAt: Date? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("watched_category_ids")
    val watchedCategoryIds: List<Any?>? = null,

    @field:SerializedName("user_auth_token_logs")
    val userAuthTokenLogs: List<Any?>? = null

) : Serializable