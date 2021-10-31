package com.homindolentrahar.githuser.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.homindolentrahar.githuser.domain.model.UserModel

data class UserDto(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    @SerializedName("login")
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    val type: String,
) {
    companion object {
        fun fromModel(model: UserModel): UserDto = UserDto(
            model.avatarUrl,
            model.htmlUrl,
            model.id,
            model.username,
            model.nodeId,
            model.type
        )
    }

    fun toModel(): UserModel = UserModel(
        id,
        nodeId,
        avatarUrl,
        name,
        htmlUrl,
        type
    )
}