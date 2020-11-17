package io.keepcoding.eh_ho.models

import com.google.gson.annotations.SerializedName

data class TopicDetails(

    @field:SerializedName("can_reply_as_new_topic")
    val canReplyAsNewTopic: Boolean? = null,

    @field:SerializedName("notifications_reason_id")
    val notificationsReasonId: Int? = null,

    @field:SerializedName("can_edit")
    val canEdit: Boolean? = null,

    @field:SerializedName("notification_level")
    val notificationLevel: Int? = null,

    @field:SerializedName("can_create_post")
    val canCreatePost: Boolean? = null,

    @field:SerializedName("can_flag_topic")
    val canFlagTopic: Boolean? = null
)