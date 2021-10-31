package com.homindolentrahar.githuser.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.homindolentrahar.githuser.domain.model.UserDetailModel

data class UserDetailDto(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val bio: String?,
    val blog: String,
    val company: String?,
    @SerializedName("created_at")
    val createdAt: String,
    val email: String?,
    val followers: Int,
    val following: Int,
    @SerializedName("hireable")
    val hireAble: String?,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val location: String?,
    val login: String,
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("public_gists")
    val publicGists: Int,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
) {
    companion object {
        fun fromModel(detailModel: UserDetailModel): UserDetailDto = UserDetailDto(
            detailModel.avatarUrl,
            detailModel.bio,
            detailModel.blog,
            detailModel.company,
            detailModel.createdAt,
            detailModel.email,
            detailModel.followers,
            detailModel.following,
            detailModel.hireAble,
            detailModel.htmlUrl,
            detailModel.id,
            detailModel.location,
            detailModel.username,
            detailModel.name,
            detailModel.nodeId,
            detailModel.publicGists,
            detailModel.publicRepos,
            detailModel.twitter,
            detailModel.type,
            detailModel.updatedAt
        )
    }

    fun toModel(): UserDetailModel = UserDetailModel(
        avatarUrl,
        bio ?: "No Bio",
        blog,
        company ?: "No Company",
        createdAt,
        email ?: "No Email",
        followers,
        following,
        hireAble ?: "Not Specified",
        htmlUrl,
        id,
        location ?: "No Location",
        login,
        name,
        nodeId,
        publicGists,
        publicRepos,
        twitterUsername ?: "No Twitter",
        type,
        updatedAt,
    )
}