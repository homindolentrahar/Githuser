package com.homindolentrahar.githuser.domain.model

data class UserModel(
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val username: String,
    val htmlUrl: String,
    val type: String,
) {
    companion object {
        fun empty(): UserModel = UserModel(
            0,
            "",
            "",
            "",
            "",
            "",
        )
    }
}
