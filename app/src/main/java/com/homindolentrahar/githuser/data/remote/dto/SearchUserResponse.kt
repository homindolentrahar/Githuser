package com.homindolentrahar.githuser.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<UserDto>
)