package com.homindolentrahar.githuser.domain.model

data class UserDetailModel(
    val avatarUrl: String,
    val bio: String,
    val blog: String,
    val company: String,
    val createdAt: String,
    val email: String,
    val followers: Int,
    val following: Int,
    val hireAble: String,
    val htmlUrl: String,
    val id: Int,
    val location: String,
    val username: String,
    val name: String,
    val nodeId: String,
    val publicGists: Int,
    val publicRepos: Int,
    val twitter: String,
    val type: String,
    val updatedAt: String,
)
